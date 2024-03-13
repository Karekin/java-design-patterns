package com.iluwatar.observer.workflow.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public class AssertUtils {

	public static void notEmpty(String text, String defaultMessage, Object... args) {
		if (StringUtils.isBlank(text)) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}

	public static void notNull(Object obj, String defaultMessage, Object... args) {
		if (obj == null) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}

	public static void isEmpty(Collection<?> collection, String defaultMessage, Object... args) {
		if (CollectionUtils.isNotEmpty(collection)) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}

	public static void isEmpty(Collection<?> collection, String defaultMessage, Supplier<Object[]> argsSupplier) {
		if (CollectionUtils.isNotEmpty(collection)) {
			throw new RuntimeException(String.format(defaultMessage, argsSupplier.get()));
		}
	}

	// 新增方法: 判断数组是否为空
	public static void isEmpty(Object[] array, String defaultMessage, Object... args) {
		if (array == null || array.length == 0) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}

	// 新增方法: 判断Map是否为空
	public static void isEmpty(Map<?, ?> map, String defaultMessage, Object... args) {
		if (map == null || map.isEmpty()) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}

	// 新增方法: 判断数字是否在指定范围内
	public static void isInRange(double number, double min, double max, String defaultMessage, Object... args) {
		if (number < min || number > max) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}

	// 新增方法: 判断字符串是否匹配正则表达式
	public static void matchesPattern(String text, String pattern, String defaultMessage, Object... args) {
		if (!text.matches(pattern)) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}

	// 新增方法: 断言表达式为真
	public static void isTrue(boolean expression, String defaultMessage, Object... args) {
		if (!expression) {
			throw new RuntimeException(String.format(defaultMessage, args));
		}
	}
}


