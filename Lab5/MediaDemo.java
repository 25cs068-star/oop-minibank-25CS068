abstract class Media
{
   public  String title;
   public int lateDay;
   Media(String title,int lateDay)
   {
    this.title=title;
    this.lateDay=lateDay;
   }

   abstract public double lateFees();
}
 class Book extends Media
 {
    Book(String title,int lateDay)
    {
        super(title,lateDay);

    }

    public double lateFees()
    {
        return lateDay*2;
    }
 }
 class Magazine extends Media
 {
    Magazine(String title,int lateDay)
     {
        super(title,lateDay);


    }
     public double lateFees()
    {
        return lateDay*4;

    }

    
 }
 class Cd extends Media {

    Cd(String title, int lateDays) {
        super(title, lateDays);
    }

     public double lateFees() {
        return lateDay* 5;
    }
}


 







public class MediaDemo {

    public static void main(String a[])
    {
        Media[] m={
            new Book("XYZ",4),
            new Magazine("ABC",4),
            new Cd("CDE",5)
            
            
            
        };

        for (Media media : m)
         {
            System.out.println("Title:"+media.title);
            
            
        }
    }
    
}
