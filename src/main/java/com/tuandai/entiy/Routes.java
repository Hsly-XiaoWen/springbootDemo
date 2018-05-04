package com.tuandai.entiy;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 肖文 on 2018/5/4
 */
@ConfigurationProperties("tdw.gateway")
public class Routes {
    private List<Route> routes = new ArrayList<>();

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public static class Route{
        private List<String> interfaces;
        private String reqDir;
        private String serverId;
        private String logDir;

        public Route() {
        }

        public Route(List<String> interfaces, String reqDir, String serverId, String logDir) {
            this.interfaces = interfaces;
            this.reqDir = reqDir;
            this.serverId = serverId;
            this.logDir = logDir;
        }

        public String getServerId() {
            return serverId;
        }

        public Route setServerId(String serverId) {
            this.serverId = serverId;
            return this;
        }

        public List<String> getInterfaces() {
            return interfaces;
        }

        public Route setInterfaces(List<String> interfaces) {
            this.interfaces = interfaces;
            return this;
        }

        public String getReqDir() {
            return reqDir;
        }

        public Route setReqDir(String reqDir) {
            this.reqDir = reqDir;
            return this;
        }

        public String getLogDir() {
            return logDir;
        }

        public Route setLogDir(String logDir) {
            this.logDir = logDir;
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Route{");
            sb.append("interfaces='").append(interfaces).append('\'');
            sb.append("reqDir='").append(reqDir).append('\'');
            sb.append(", serverId='").append(serverId).append('\'');
            sb.append(", logDir='").append(logDir).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}