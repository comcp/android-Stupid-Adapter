package com.stupid.method.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.stupid.method.http.util.StringUtils;

/**
 * JSON 数据的常用方法的工具类
 * 
 */
public class JsonUtils {
	/** 空的 JSON 数据 */
	public static final String EMPTY_JSON = "{}";

	/** 空的 JSON 数组(集合)数据 */
	public static final String EMPTY_JSON_ARRAY = "[]";

	/** 默认的 JSON 日期/时间字段的格式化模式 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

	public JsonUtils() {
		// JSON.DEFFAULT_DATE_FORMAT = DEFAULT_DATE_PATTERN;
	}

	/** 防止出现json exception */
	public static <T> T parseObject(String json, Class<T> cls) {
		T t = null;
		try {

			t = JSON.parseObject(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/** 防止出现json exception */
	public static <T> List<T> parseArray(String json, Class<T> cls) {

		List<T> t = null;
		try {

			t = JSON.parseArray(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 JSON 格式的字符串。
	 * 
	 * @param target
	 *            目标对象。
	 * @return 目标对象的 JSON 格式的字符串。
	 */
	public static String toJSONString(Object target) {
		if (target == null)
			return EMPTY_JSON;
		return JSON.toJSONString(target);
	}

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 JSON 格式的字符串。
	 * 
	 * @param target
	 *            目标对象。
	 * @param prettyFormat
	 *            是否格式JSON文本。
	 * @return 目标对象的 JSON 格式的字符串。
	 */
	public static String toJSONString(Object target, boolean prettyFormat) {
		if (target == null)
			return EMPTY_JSON;
		return JSON.toJSONString(target, prettyFormat);
	}

	/**
	 * 将给定的目标对象转换成 JSONObject或JSONArray。
	 * 
	 * @param target
	 *            要转换成 JSON 的目标对象。
	 * @return 目标对象的 JSON 格式的字符串。
	 */
	public static Object toJsonObject(Object target) {
		if (target == null)
			return EMPTY_JSON;
		return JSON.toJSON(target);
	}

	/**
	 * 将给定的目标日期对象转换成 JSON 日期格式的字符串。
	 * 
	 * @param target
	 *            要转换成 JSON 的目标日期对象。
	 * @return 目标对象的 JSON 日期格式的字符串。
	 */
	public static String toJsonDateFormat(Object date) {
		if (date == null)
			return EMPTY_JSON;
		return JSON.toJSONStringWithDateFormat(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 将给定的目标对象根据指定的特征条件参数转换成 JSON 格式的字符串。
	 * 
	 * @param target
	 *            目标对象。
	 * @param isGlobalFormatDate
	 *            全局修改日期格式。
	 * @param feature
	 *            SerializerFeature.DisableCircularReferenceDetect 特性关闭引用检测和生成
	 *            SerializerFeature.BrowserCompatible 兼容IE6不支持JSON带中文字符串
	 *            SerializerFeature.UseISO8601DateFormat ISO-8601日期格式
	 *            SerializerFeature.WriteDateUseDateFormat 全局修改日期格式
	 *            SerializerFeature.UseSingleQuotes 使用单引号
	 *            SerializerFeature.WriteClassName 支持全序列化
	 *            SerializerFeature.WriteMapNullValue 输出空置字段
	 *            SerializerFeature.WriteNullListAsEmpty list字段如果为null,输出为[]
	 *            SerializerFeature.WriteNullNumberAsZero 数值字段如果为null,输出为0
	 *            SerializerFeature.WriteNullBooleanAsFalse
	 *            Boolean字段如果为null,输出为false
	 *            SerializerFeature.WriteNullStringAsEmpty 字符类型字段如果为null,输出为""
	 * @return 目标对象的 JSON 格式的字符串。
	 */
	public static String toJSONString(Object target,
			SerializerFeature... feature) {
		if (target == null)
			return EMPTY_JSON;
		return JSON.toJSONString(target, feature);
	}

	/**
	 * 将给定的目标对象根据指定的过滤参数转换成 JSON 格式的字符串。
	 * 
	 * @param target
	 * @param clazz
	 * @param properties
	 * @return
	 */
	public static String toJSONString(Object target, Class<?> clazz,
			String... properties) {
		if (target == null)
			return EMPTY_JSON;
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz,
				properties);
		return JSON.toJSONString(target, filter);
	}

	/**
	 * 将给定的目标对象根据指定的过滤参数转换成 JSON。
	 * 
	 * @param target
	 * @param clazz
	 * @param properties
	 * @return
	 */
	public static Object toJson(Object target, Class<?> clazz,
			String... properties) {
		String obj = toJSONString(target, clazz, properties);
		return JSON.parse(obj);
	}

	/**
	 * 将给定的 JSON 字符串转换成Object对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 JSON 字符串。
	 */
	public static Object fromJson(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Object object = null;
		try {
			object = JSON.parseObject(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return object;
	}

	/**
	 * 将给定的 JSON 字符串转换成指定的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 JSON 字符串。
	 * @return 给定的 JSON 字符串表示的指定的类型对象。
	 */
	public static <T> T fromJson(String json, Class<T> classType) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		T t = null;
		try {
			t = JSON.parseObject(json, classType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return t;
	}

	/**
	 * 将给定的 JSON 字符串转换成指定的泛型类型对象。
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJsonGeneric(String json, TypeReference<T> type) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		T t = null;
		try {
			t = JSON.parseObject(json, type);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return t;
	}

	/**
	 * 将给定的 JSON 字符串转换成指定Map的类型对象。
	 * 
	 * @param json
	 *            给定的 JSON 字符串。
	 * @return 给定的 JSON 字符串表示的指定的类型对象。
	 */
	public static Map<String, String> fromJsonMapString(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = JSON.parseObject(json,
					new TypeReference<Map<String, String>>() {
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	/**
	 * 将给定的 JSON 字符串转换成指定MapObject的类型对象。
	 * 
	 * @param json
	 *            给定的 JSON 字符串。
	 * @return 给定的 JSON 字符串表示的指定的类型对象。
	 */
	public static Map<String, Object> fromJsonMapObject(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = JSON.parseObject(json,
					new TypeReference<Map<String, Object>>() {
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	/**
	 * 将给定的 JSON 字符串转换成JsonArray的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 JSON 字符串。
	 * @return 给定的 JSON 字符串表示的指定的类型对象。
	 */
	public static JSONArray fromJsonArray(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		JSONArray array = new JSONArray();
		try {
			array = JSON.parseArray(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return array;
	}

	/**
	 * 将给定的 JSON 字符串转换成指定List的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 JSON 字符串。
	 * @return 给定的 JSON 字符串表示的指定的类型对象。
	 */
	public static <T> List<T> fromJsonList(String json, Class<T> classType) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(json, classType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 将给定的 JSON 字符串转换成指定MapList的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 JSON 字符串。
	 * @return 给定的 JSON 字符串表示的指定的类型对象。
	 */
	public static List<Map<String, Object>> fromJsonMapList(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = JSON.parseObject(json,
					new TypeReference<List<Map<String, Object>>>() {
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

}
