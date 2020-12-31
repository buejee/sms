package sms.runtime;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sms.service.StudentService;

public class CommandRunner {
	
	private Map<String, Object> services = new HashMap<>();
	private Map<String, Map<String, Method>> methods = new HashMap<>();
	private Map<String, Class<?>> serviceTypes = Map.of("student",StudentService.class);
	
	public CommandRunner() {
		serviceTypes.forEach((type,clazz)->{
			ServiceLoader.load(clazz).findFirst().ifPresent(obj->{
				services.put(type, obj);
				methods.put(type, Stream.of(obj.getClass().getDeclaredMethods()).collect(Collectors.toMap(Method::getName, Function.identity())));
			});
		});
	}
	
	public void run(String service,String task,List<Object> args) {
		Object serviceObject = services.get(service);
		Method method= methods.get(service).get(task);
		try {
			Object result = method.invoke(serviceObject, args.toArray());
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @SuppressWarnings("resource")
	public static void main( String[] args ){
    	CommandRunner commandRunner = new CommandRunner();
    	Scanner scanner= new Scanner(System.in);
    	
    	String line;
    	while(!Objects.equals((line=scanner.nextLine()), "quit")) {
    		String[] inputs = line.split("\\W");
    		commandRunner.run(inputs[0], inputs[1], Arrays.asList(Arrays.copyOfRange(inputs, 2, inputs.length)));
    	}
    }
}
