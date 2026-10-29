# =====================================================
# ETAPA 1: DESCARGAR Y COMPILAR LA APLICACIÓN
# =====================================================
FROM maven:3.9-eclipse-temurin-21 AS build

RUN apt-get update \
    && apt-get install -y --no-install-recommends git \
    && rm -rf /var/lib/apt/lists/*

ARG GIT_BRANCH=main

WORKDIR /src

RUN git clone \
    --depth 1 \
    --single-branch \
    --branch "${GIT_BRANCH}" \
    https://gitlab.com/tutorial-j2ee-spring-boot/j2ee-basis.git \
    .

RUN mvn -B clean package

# Comprobar que se genera exactamente un WAR
RUN set -eu; \
    count=0; \
    for war in /src/target/*.war; do \
        if [ -f "$war" ]; then \
            count=$((count + 1)); \
            WAR_FILE="$war"; \
        fi; \
    done; \
    if [ "$count" -ne 1 ]; then \
        echo "ERROR: se esperaba exactamente un fichero WAR; encontrados: $count"; \
        exit 1; \
    fi; \
    cp "$WAR_FILE" /tmp/ROOT.war

# =====================================================
# ETAPA 2: DESPLEGAR EN TOMCAT 11
# =====================================================
FROM tomcat:11

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /tmp/ROOT.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
