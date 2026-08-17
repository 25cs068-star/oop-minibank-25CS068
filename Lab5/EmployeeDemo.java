abstract  class Employee {

    public int id;
    public String name;
    Employee(int id, String name)
    {
        this.id=id;
        this.name=name;

    }
    abstract  public double monthlySalary();

     
}

class FullTime extends Employee
{
   
    double fixed;


    FullTime(int id,String name,double fixed)
    {
        super(id,name);

        this.fixed=fixed;
    }

    public double monthlySalary()
    {
        return fixed;
    }
}
class PartTime extends Employee
{
    int hours;
    double rate;
    PartTime(int id,String name,int hours,double rate)
    {
        super(id,name);
        this.hours=hours;
        this.rate=rate;
        
    }

    public double monthlySalary()
    {
        return hours*rate;
    }
}
class Intern extends Employee
{
    double stipend;
    Intern(int id,String name,double stipend)
    {
        super(id, name);
        this.stipend=stipend;
    }
    public double monthlySalary()
    {
        return stipend;
    }
}

public class EmployeeDemo
{
    public static void main(String arg[])
    {
        Employee[] employees={
            new FullTime(25,"Krisha",30000),
            new PartTime(24,"Heni",2,2000),
            new Intern(34, "Honey",2000)
        };

        for(Employee e:employees)
        {
            System.out.println("Employee Name:"+e.name);
            System.out.println("Employee Id:"+e.id);
            
                if(e instanceof Intern)
                {
                    System.out.println("Intern don't have salary they have stipends ");
                }
                else
                    System.out.println("Monthly Salary:"+e.monthlySalary());
            System.out.println("_____________________");


        }

        




    }
}


