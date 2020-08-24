package first;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
* @Description: ActiveMq
 *              入门-01
 *              消费者
* @Author: maiBangMin
* @date: 2020/8/24 16:20
* @Version: 1.0
*/
public class Consumer{

    public static void main(String[] args) throws JMSException {

        // 1.创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );
        // 2.创建连接
        Connection connection = factory.createConnection();
        // 3.创建session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        // 4.创建消费者
        Queue first = session.createQueue("first");
        MessageConsumer consumer = session.createConsumer(first);
        // 5.接受消息
        while (true){
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("[C-message]" + message.getText());
        }
    }

}
