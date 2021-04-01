package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		
		Map<Object, Object> map = new HashMap<>();
		
		dePopulate(obj, map);
		// map이 비어있다가 dePopulate를 거치면 map이 채워지게 된다.
		System.out.println(map);

		
	}
	
	public static void dePopulate(Object bean, Map<Object, Object> map) {
		Class clz = bean.getClass();
		Field[] fields = clz.getDeclaredFields();
		for(Field tmp : fields) {
			String varName = tmp.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);
				Class varType = pd.getPropertyType();
				Method getter = pd.getReadMethod();	//ReadMethod는 getter, WriteMethod는 setter를 리턴해준다.
				Object value = getter.invoke(bean);
				
				Method setter = pd.getWriteMethod();
				setter.invoke(bean, "");
				
				System.out.printf("%s %s = %s;\n", varType.getSimpleName(), varName, value);
				
				map.put(varName, value);
			} catch (IntrospectionException e) {
				// PropertyDescriptor 는 javaBean 규약에 맞게 생성된 객체를 전제로 하고 있는데
				// 우리는 여기에서 그 규약을 따르고 있는지 알 수 없으므로
				// 예외가 발생할 경우가 있다 이를 위해 IntrospectionException을 샤용
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	// obj가 javaBean 규약을 지켰다는 전제하에 처리하는 Reflection 방식
	private static void reflect2(Object obj) {
		Class clz = obj.getClass();
		Field[] fields = clz.getDeclaredFields();
		for(Field tmp : fields) {
			String varName = tmp.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);
				Class varType = pd.getPropertyType();
				Method getter = pd.getReadMethod();	//ReadMethod는 getter, WriteMethod는 setter를 리턴해준다.
				Object value = getter.invoke(obj);
				System.out.printf("%s %s = %s;\n", varType.getSimpleName(), varName, value);
			} catch (IntrospectionException e) {
				// PropertyDescriptor 는 javaBean 규약에 맞게 생성된 객체를 전제로 하고 있는데
				// 우리는 여기에서 그 규약을 따르고 있는지 알 수 없으므로
				// 예외가 발생할 경우가 있다 이를 위해 IntrospectionException을 샤용
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	// 옛날 방식의 reflect 방식
	private static void reflect1(Object obj) {
		Class clz = obj.getClass();
		System.out.println(clz);
		Field[] fields = clz.getDeclaredFields();
		
		for(Field tmp : fields) {
			String varName = tmp.getName();
			Class varType = tmp.getType();
			try {
				// 객체의 private 상태를 public 으로 바꿀 수가 있음 (어디에서든 접근 가능하게 할 수 있다)
//				tmp.setAccessible(true);
//				Object value = tmp.get(obj);
				String readMethodName = "get" + varName.substring(0,1).toUpperCase() + varName.substring(1);
				Method readMethod = clz.getDeclaredMethod(readMethodName);
				// member.getMem_name();
				Object value = readMethod.invoke(obj);
				
				System.out.printf("%s %s=%s;\n", varType.getSimpleName(), varName, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// 메소드에 잘못된 파라미터가 넘어갔을 때 발생하는 예외
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// invoke를 할 때 새로운 객체가 생성되어 파라미터로 넘어갈 때 발생하는 예외
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
