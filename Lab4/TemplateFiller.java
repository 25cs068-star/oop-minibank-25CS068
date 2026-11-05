import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TemplateFillerDemo
{
    public static String fillTemplate(String template, String[] names, String[] values)
    {
        // Compile pattern to find placeholders
        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();
        int lastEnd = 0;

        // Find each placeholder in the template
        while(matcher.find())
        {
            result.append(template, lastEnd, matcher.start());

            String placeholder = matcher.group(1);
            String replacement = "[?]";

            // Search for placeholder name in names array
            for(int i = 0; i < names.length; i++)
            {
                if(names[i].equals(placeholder))
                {
                    replacement = values[i];
                    break;
                }
            }

            // Replace placeholder with its value
            result.append(replacement);
            lastEnd = matcher.end();
        }

        // Append remaining text after the last placeholder
        result.append(template, lastEnd, template.length());

        return result.toString();
    }
}

public class TemplateFiller
{
    public static void main(String[] args)
    {
        String template = "Dear {name}, order {id} ships {date}.";

        String[] names = {"name", "id"};
        String[] values = {"Krisha", "A07"};

        // Fill template using the given values
        String result = TemplateFillerDemo.fillTemplate(template, names, values);

        System.out.println(result);
    }
}