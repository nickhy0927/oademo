package com.iss.common.createPage.xml.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.springframework.stereotype.Service;

import com.iss.common.createPage.utils.CreatePage;
import com.iss.common.createPage.xml.XmlTemplate;
import com.orm.annotation.Table;
import com.orm.utils.FileTools;
import com.orm.utils.Underline2Camel;

@Service
public class XmlTemplateImpl implements XmlTemplate {

	public void getXml(CreatePage createPage) throws Exception {
		StringBuffer XML = namespaces(createPage);
		File file = new File("C:\\Users\\Hyuan\\Desktop");
		String path = file.getAbsolutePath() + "/" + createPage.getClassName() + ".xml";
		file = new File(path);
		try {
			if (!file.exists()) {
				file.createNewFile();
				FileTools.WriteStringToFile(path, XML.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StringBuffer namespaces(CreatePage createPage) throws Exception {
		StringBuffer xml = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Table table = clazz.getAnnotation(Table.class);
		if (table == null) {
			throw new Exception("类" + createPage.getFullPath() + "没有table注解，请检查。");
		}
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		xml.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
		xml.append(mapper(createPage));
		return xml;
	}

	private StringBuffer mapper(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<mapper namespace=\"" + createPage.getFullPath() + "\">\r\n");
		buffer.append(insert(createPage));
		buffer.append("\r\n");
		buffer.append(delete(createPage));
		buffer.append("\r\n");
		buffer.append(deleteBatch(createPage));
		buffer.append("\r\n");
		buffer.append(update(createPage));
		buffer.append("\r\n");
		buffer.append(get(createPage));
		buffer.append("\r\n");
		buffer.append(queryAll(createPage));
		buffer.append("\r\n");
		buffer.append(queryListByMap(createPage));
		buffer.append("\r\n");
		buffer.append(queryPageByMap(createPage));
		buffer.append("\r\n");
		buffer.append(conditions(createPage));
		buffer.append("</mapper>\r\n");
		return buffer;
	}

	private StringBuffer insert(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Field[] fields = clazz.getSuperclass().getDeclaredFields();
		buffer.append("	<insert id=\"insert\" parameterType=\"" + createPage.getFullPath() +"\">\r\n");
		Table table = clazz.getAnnotation(Table.class);
		buffer.append("		insert into ").append(table.name()).append("(");
		StringBuffer fieldBuffer = new StringBuffer();
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			fieldBuffer.append(Underline2Camel.camel2Underline(field.getName())).append(",");
			valueBuffer.append("#{").append(field.getName()).append("}").append(",");
		}
		fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (i == fields.length - 1) {
				fieldBuffer.append(Underline2Camel.camel2Underline(field.getName()));
			} else
				fieldBuffer.append(Underline2Camel.camel2Underline(field.getName())).append(",");
			if (i == fields.length - 1) {
				valueBuffer.append("#{").append(field.getName()).append("}");
			} else
				valueBuffer.append("#{").append(field.getName()).append("},");
		}
		buffer.append(fieldBuffer).append(")\r\n");
		buffer.append("		values (");
		buffer.append(valueBuffer);
		buffer.append("		)\r\n");
		buffer.append("	</insert>\r\n");
		return buffer;
	}

	private StringBuffer delete(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		buffer.append("	<delete id=\"delete\" parameterType=\"java.lang.String\">\r\n");
		buffer.append("		delete from ");
		buffer.append(clazz.getAnnotation(Table.class).name()).append(" where id = #{id}\r\n");
		buffer.append("	</delete>\r\n");
		return buffer;
	}

	private StringBuffer deleteBatch(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		buffer.append("	<delete id=\"deleteBatch\" parameterType=\"java.util.List\">\r\n");
		buffer.append("		delete from ");
		buffer.append(clazz.getAnnotation(Table.class).name()).append(" where id in \r\n");
		buffer.append("		<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">\r\n");
		buffer.append("			#{id}\r\n");
		buffer.append("		</foreach>\r\n");
		buffer.append("	</delete>\r\n");
		return buffer;
	}

	private StringBuffer update(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Table table = clazz.getAnnotation(Table.class);
		buffer.append("	<update id=\"update\" parameterType=\"" + createPage.getFullPath() +"\">\r\n");
		buffer.append("		update ").append(table.name()).append(" \r\n");
		buffer.append("		<trim prefix=\"set\" prefixOverrides=\",\">\r\n");
		buffer.append("			1=1\r\n");
		Field[] fields = clazz.getSuperclass().getDeclaredFields();
		for (Field field : fields) {
			String fname = Underline2Camel.camel2Underline(field.getName());
			buffer.append("			<if test=\""+field.getName()+" != null\">" + fname + " = #{" + field.getName() + "},</if>\r\n");
		}
		fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fname = Underline2Camel.camel2Underline(field.getName());
			buffer.append("			<if test=\""+field.getName()+" != null\">" + fname + " = #{" + field.getName() + "},</if>\r\n");
		}
		buffer.append("		</trim>");
		buffer.append("	</update>\r\n");
		return buffer;
	}

	private StringBuffer get(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Table table = clazz.getAnnotation(Table.class);
		buffer.append("	<select id=\"get\" parameterType=\"String\" resultType=\"" + createPage.getFullPath() + "\">\r\n");
		buffer.append("		select * from ").append(table.name()).append(" where id = #{id}\r\n");
		buffer.append("	</select>\r\n");
		return buffer;
	}

	private StringBuffer conditions(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Field[] fields = clazz.getSuperclass().getDeclaredFields();
		buffer.append("	<sql id=\"conditions\">\r\n");
		buffer.append("		<trim prefix=\"where\" prefixOverrides=\"and|or\">\r\n");
		for (Field field : fields) {
			String fname = Underline2Camel.camel2Underline(field.getName());
			buffer.append("			<if test=\""+field.getName()+" != null\">and " + fname + " = #{" + field.getName() + "}</if>\r\n");
		}
		fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fname = Underline2Camel.camel2Underline(field.getName());
			buffer.append("			<if test=\"" + field.getName() + " != null\">and " + fname + " = #{" + field.getName() + "}</if>\r\n");
		}
		buffer.append("			<if test=\"endRow != null\"> limit ${endRow}, ${pageSize}</if>\r\n");
		buffer.append("		</trim>\r\n");
		buffer.append("	</sql>\r\n");
		return buffer;
	}
	
	private StringBuffer queryAll(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Table table = clazz.getAnnotation(Table.class);
		buffer.append("	<select id=\"queryAll\" parameterType=\"String\" resultType=\"" + createPage.getFullPath() + "\">\r\n");
		buffer.append("		select * from ").append(table.name()).append("\r\n");
		buffer.append("	</select>\r\n");
		return buffer;
	}

	private StringBuffer queryListByMap(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Table table = clazz.getAnnotation(Table.class);
		buffer.append("	<select id=\"queryListByMap\" parameterType=\"java.util.Map\" resultType=\"" + createPage.getFullPath() + "\">\r\n");
		buffer.append("		select * from ").append(table.name()).append("\r\n");
		buffer.append("			where 1 = 1\r\n");
		buffer.append("			<include refid=\"conditions\" />\r\n");
		buffer.append("	</select>\r\n");
		return buffer;
	}

	private StringBuffer queryPageByMap(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Table table = clazz.getAnnotation(Table.class);
		buffer.append("	<select id=\"queryPageByMap\" parameterType=\"java.util.Map\" resultType=\"" + createPage.getFullPath() + "\">\r\n");
		buffer.append("		select * from ").append(table.name()).append("\r\n");
		buffer.append("			where 1 = 1\r\n");
		buffer.append("			<include refid=\"conditions\" />\r\n");
		buffer.append("	</select>\r\n");
		return buffer;
	}

}
