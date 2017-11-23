import com.ibm.mq.jms.MQTopic;
import com.ibm.mq.jms.MQTopicConnectionFactory;
import com.ibm.msg.client.commonservices.propertystore.PropertyStore;

import javax.jms.*;

/**
 * @author luismoramedina
 */
public class Sender {

	public static void main(String[] args) throws Exception {
		PropertyStore.set("com.ibm.msg.client.commonservices.trace.status", "ON");
		send((MQTopicConnectionFactory) Connection.mqTopicConnectionFactory(
				args[0], Integer.parseInt(args[1]), args[2], args[3]), args[4], args[5], args[6]);
		System.out.println("Sent");
	}

	private static void send(MQTopicConnectionFactory mqTopicConnectionFactory, String topicName, String user, String pass) throws JMSException {
		TopicConnection topicConnection = mqTopicConnectionFactory.createTopicConnection(user, pass);
		TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		MQTopic topic = new MQTopic(topicName);
		TopicPublisher publisher = topicSession.createPublisher(topic);
		Message message = topicSession.createMessage();
		message.setStringProperty("hu", "ha");
		publisher.publish(message);
	}


}
