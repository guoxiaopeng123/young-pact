package com.young.pact.aop;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


public class MainConfigOfAOP {
	 

	//切面类加入到容器中
	public LogAspects logAspects(){
		return new LogAspects();
	}
	
}

