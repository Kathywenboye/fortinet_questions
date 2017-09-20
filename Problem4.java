package fortinet;

public class Problem4 {
	//Implements of  Singleton
	public static class Person {
		  private final String name;
		  private final int age;
		  
		  private Person(String name, int age){
		    this.name = name;
		    this.age = age;
		  }
		  private static Person person = null;
		  public static Person getPerson(){
			  if(person == null)
				  person = new Person("Allen", 28);
			  return person;
		  }
		  public String toString(){
			  return  "Person: " + name + " " + age;
		  }
		}
}
