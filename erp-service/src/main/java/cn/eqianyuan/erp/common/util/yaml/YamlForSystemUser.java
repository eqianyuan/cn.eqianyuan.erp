package cn.eqianyuan.erp.common.util.yaml;

import cn.eqianyuan.erp.entity.SystemUserBo;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * 系统用户yaml文件操作类
 * Created by jason on 2016/1/9.
 */
public class YamlForSystemUser {

    /**
     * 私有化默认构造函数
     * 禁止外部构造，减少内存开销
     */
    private YamlForSystemUser() {
    }

//    private static ServerYamlHandleUtil serverYamlHandleUtil = new ServerYamlHandleUtil();

//    public static ServerYamlHandleUtil getInstance() {
//        if(serverYamlHandleUtil == null){
//            serverYamlHandleUtil = new ServerYamlHandleUtil();
//        }
//
//        return serverYamlHandleUtil;
//    }

    /**
     * 系统服务用户集合对象
     */
    private static List<SystemUserBo> systemUserList;

    /**
     * 系统用户数据配置文件名称
     */
    private static final String YAML_NAME = "system-user.yaml";

    /**
     * 通过解析系统用户yaml配置文件，获取用户信息数据集合
     * @return
     */
    public static List<SystemUserBo> getSystemUserList() {
        if (Objects.isNull(systemUserList)) {
            Yaml yaml = new Yaml();

            /**
             * 如果配置文件在jar包中，则需要使用流加载方式读取
             */
//            yaml.loadAs(this.getClass().getResourceAsStream("/user.yaml"), SystemUserBo.class);

            //从yaml配置文件中获取系统用户信息数据集合
            systemUserList = yaml.loadAs(YamlForSystemUser.class.getClassLoader().getResourceAsStream(YAML_NAME), List.class);
        }
        return systemUserList;
    }

    private static void main(String[] args) throws FileNotFoundException, URISyntaxException {
//        Yaml yaml = new Yaml();
//        URL url = ServerYamlHandleUtil.class.getClassLoader().getResource("user.yaml");
//        System.out.println(url.getPath());
//        if(url != null){
//            Object obj2 = yaml.load(new FileInputStream(url.toURI().getPath()));
//
//            System.out.println(obj2);
//        }

//        Yaml y = new Yaml();
//        Object obj = y.load(ServerYamlHandleUtil.class.getResourceAsStream("/user.yaml"));
//        System.out.println(obj);

//        List<SystemUserBo> systemUserBos = y.loadAs(ServerYamlHandleUtil.class.getResourceAsStream("/user.yaml"), List.class);
//        for (SystemUserBo systemUserBo : systemUserBos) {
//            System.out.println(systemUserBo.getUserName());
//        }
    }
}
