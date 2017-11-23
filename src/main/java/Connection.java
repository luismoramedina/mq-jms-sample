import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQTopicConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

/**
 * @author luismoramedina
 */
public class Connection {

	public static MQConnectionFactory mqTopicConnectionFactory(
			String host, int port, String queueManager, String channel){
		MQTopicConnectionFactory mqTopicConnectionFactory = new MQTopicConnectionFactory();
		try {
			mqTopicConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
			mqTopicConnectionFactory.setPort(port);
			mqTopicConnectionFactory.setQueueManager(queueManager);
			mqTopicConnectionFactory.setHostName(host);
			mqTopicConnectionFactory.setChannel(channel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mqTopicConnectionFactory;
	}
}
