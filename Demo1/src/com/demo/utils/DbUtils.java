package com.demo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.demo.db.DBOperator;
import com.demo.model.FiledMapper;


public class DbUtils {
	public static void mapParseObject(Object object, Map map) throws Exception {
		List<Field> fields = new ArrayList<Field>();
		Class clazz = object.getClass();
		while(clazz!=null){
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		for (Field field : fields) {
			if (map.get(field.getName()) != null) {
				org.apache.commons.beanutils.BeanUtils.setProperty(object,
						field.getName(), map.get(field.getName()));
			} else {
				boolean hasAnnotation = field.isAnnotationPresent(FiledMapper.class);
				if (hasAnnotation) {
					FiledMapper annotation = field.getAnnotation(FiledMapper.class);
					org.apache.commons.beanutils.BeanUtils.setProperty(object, field.getName(), map.get(annotation.filed()));
				}
			}

		}
	}
}
