<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss}] Usuario: %X{usuario} - Acción: %msg - Resultado: %X{resultado} - Detalles: %X{detalles}%n"/>
        </Console>
        <File name="File" fileName="banco.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss}] Usuario: %X{usuario} - Acción: %msg - Resultado: %X{resultado} - Detalles: %X{detalles}%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="debug">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>