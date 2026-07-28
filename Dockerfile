FROM quay.io/wildfly/wildfly:latest
# 作成したwarファイルを、WildFlyのデプロイ領域にコピー
COPY target/*.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
