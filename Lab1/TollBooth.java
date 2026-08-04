import java.util.*;
record Vehical(String number ,String type){}
class TollBooth
{
public static void main(String a[])
{
//Vehical v1=new Vehical("134","Bike");
//System.out.println(v1);

int totalToll=0;
int bike=0;
int car=0;
int truck=0;
Scanner sc= new Scanner(System.in);

while(true)
{
String type=sc.next();
if(type.equalsIgnoreCase("done"))
break;

String number=sc.next();
Vehical v=new Vehical(number,type);
int toll=0;

switch(v.type())
{
case "Bike":toll=20;
bike++;
break;

case "Car":toll=50;
car++;
break;

case "Truck":toll=150;
truck++;
break;


default:toll=0;
}

System.out.println(v.type()+"passed With Toll:"+toll);
totalToll+=toll;

}

System.out.println("Total Bike Passed:"+bike);
System.out.println("Total car Passed:"+car);
System.out.println("Total truck Passed:"+truck);
System.out.println("Total toll Collected:"+totalToll);

}
}
