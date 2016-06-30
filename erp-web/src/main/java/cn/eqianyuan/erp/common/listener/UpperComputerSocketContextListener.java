package cn.eqianyuan.erp.common.listener;

import cn.eqianyuan.erp.common.constant.SystemConstant;
import org.apache.log4j.Logger;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * 探测器上位机通信监听器
 *
 * @author jason
 */
public class UpperComputerSocketContextListener implements ServletContextListener {

    Logger logger = Logger.getLogger(this.getClass());

    //与上位机通信监听的端口
    private static final int PORT = 33633;
    //与上位机通信监听的客户端列队数
    private static final int BACKLOG = 100;
    //todo 可删除
    public static byte status = 0;
    //服务器
    ServerSocket server = null;
    //上位机通信对象
    Socket client = null;

    /**
     * 定时清空临时附件目录过期附件
     */
    public void contextInitialized(ServletContextEvent sce) {
        logger.info(" 探测器上位机通讯接口初始化 ...");
        //准备通信服务对象
        try {
            server = new ServerSocket(PORT, BACKLOG);
        } catch (IOException e) {
            logger.error(" 准备通信服务对象时失败，可能出现的原因：" +
                    "\n * 端口已经被其他服务器进程占用 " +
                    "\n * 没有以超级用户的身份来运行服务器程序, 没有权限监听 1-1023 之间的端口", e);
            //退出系统
            //System.exit(1);
        }
        logger.info(" 探测器上位机通讯接口准备就绪 ！");

        //使用独立线程维护SOCKET服务通信接口
        SystemConstant.PARKING_UPPER_COMPUTER_THREAD_POOL.execute(new Runnable() {
            public void run() {
                while (true) {
                    //从客户端通信列队中获取一个建立通信连接
                    try {
                        client = server.accept();
                    } catch (IOException e) {
                        logger.warn("获取客户端请求建立连接失败");
                    }

                    SystemConstant.PARKING_UPPER_COMPUTER_THREAD_POOL.execute(new OperationHand(client));
                }
            }
        });
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    /**
     * 上位机请求处理
     */
    class OperationHand implements Runnable {

        private Socket client;

        OperationHand(Socket client) {
            this.client = client;
        }

        public void run() {
            logger.info(" 发现新连接:" + (ObjectUtils.isEmpty(client.getInetAddress()) ? "" :
                    client.getInetAddress()) + ":" + (client.getPort() > 0 ? client.getPort() : 0));

            InputStream is;
            /**
             * 从上位机获取报送数据
             * 数据总长度：9个字节
             * byte[0]   开始字符
             * byte[1 - 3]   地区行政编号
             * byte[4 - 5]      上位机编号
             * byte[6]      探测器终端编号
             * byte[7]      探测器终端状态
             * byte[8]      探测器终端位置是否预约
             */
            byte[] upperComputerContent;

            //设置socket读取输入流超时时间，超过时间则关闭socket
            try {
                logger.info("设置socket读取流超时时间20秒钟");
                client.setSoTimeout(20 * 1000);
            } catch (SocketException e) {
                logger.warn("等待客户端请求输入流超时");
                try {
                    client.close();
                } catch (IOException e1) {
                    logger.error("关闭客户端连接失败...", e);
                }
            }

            try {
                while (true) {
                    //由Socket对象得到输入流，并构造相应的BufferedReader对象
                    is = client.getInputStream();
                    upperComputerContent = new byte[9];
                    is.read(upperComputerContent, 0, 9);

                    logger.info("***********************************************");
                    logger.info("接收到一条新通信信息");
                    logger.info("上位机数据:");
                    StringBuilder content = new StringBuilder();
                    for (byte b : upperComputerContent) {
                        content.append(" ").append(b);
                    }
                    logger.info(content);

                    status = upperComputerContent[7];
                    logger.info("状态：" + (status == 1 ? "空闲" : status == 2 ? "有障碍物" : "其他"));
                    logger.info("***********************************************");

                    if(upperComputerContent[0] == 0){
                        logger.info("关闭客户端socket连接");
                        client.close();
                    }
                }
            } catch (Exception e) {
                logger.warn(" 从客户端中获取I/O错误：{}", e);
                logger.info("关闭客户端socket连接");
                try {
                    client.close();
                } catch (IOException e1) {
                    logger.error("关闭客户端连接失败...", e);
                }
            }
        }
    }
}
