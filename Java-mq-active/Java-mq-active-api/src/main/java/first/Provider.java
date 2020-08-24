package first;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
* @Description: ActiveMq
 *              入门-01
 *              提供者
* @Author: maiBangMin
* @date: 2020/8/24 15:57
* @Version: 1.0
*/
public class Provider {

    public static void main(String[] args) throws JMSException {

        // 1.创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616");

        // 2.创建连接
        Connection connection = factory.createConnection();

        // 3.创建session
        // 第一个参数 boolean 是否开启事务
        // 第二个参数 Session 应答模式
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        // 4.创建提供者
        // Destination 目的地名称
        Queue first = session.createQueue("first");
        MessageProducer producer = session.createProducer(first);

        // 5.发送消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("[P- message]" + i);
            producer.send(textMessage);
        }

        // 6.关闭连接
        session.close();
        connection.close();
    }


}
