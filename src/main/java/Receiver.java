import com.ibm.mq.jms.MQTopic;
import com.ibm.mq.jms.MQTopicConnectionFactory;
import com.ibm.msg.client.commonservices.propertystore.PropertyStore;

import javax.jms.*;

/**
 * @author luismoramedina
 */
public class Receiver {

	public static void main(String[] args) throws Exception {
		PropertyStore.set("com.ibm.msg.client.commonservices.trace.status", "ON");
		receive((MQTopicConnectionFactory) Connection.mqTopicConnectionFactory(
				args[0], Integer.parseInt(args[1]), args[2], args[3]), args[4], args[5], args[6]);
	}

	private static void receive(
			MQTopicConnectionFactory mqTopicConnectionFactory, String topicName, String user, String pass) throws JMSException {
		TopicConnection topicConnection = mqTopicConnectionFactory.createTopicConnection(user, pass);
		topicConnection.start();
		TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		MQTopic topic = new MQTopic(topicName);
		TopicSubscriber subscriber = topicSession.createSubscriber(topic);

		System.out.println("Receiving");
		Message receive = subscriber.receive();
		System.out.println("Message received: " + receive.getStringProperty("hu"));
	}


}
