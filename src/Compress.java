import java.io.*;

public class Compress {
File file;
String path;

    public static String compress(String s){
        char temp=s.charAt(0);
        int count=1;
        String result="";
        int i=1;
        for(i=1;i<s.length();i++){

            if(temp==s.charAt(i)){
                count++;
            }
            else {
                result=result+temp+count;

                count=1;
            }



            temp=s.charAt(i);
        }


        //the last one
        result=result+temp+count;


        return result;
    }



    public static String un_compress(String s){



        String result="";
        int i=1;
        String number="";
        char last_char=s.charAt(0);

        for(i=1;i<s.length();i++){


            if(Character.isDigit(s.charAt(i))){ //is int
                number=number+s.charAt(i);

            }
            else {
                for(int j=0;j<Integer.parseInt(number);j++) {

                    result = result +last_char;

                }
                number="";
                last_char=s.charAt(i);
            }
        }


        ///the last char
        for(int j=0;j<Integer.parseInt(number);j++) {

            result = result +last_char;

        }




        return result;
    }
public String Compress1(String path) throws IOException {


        this.path=path;
        File file =new  File(this.path);
        BufferedReader br=new BufferedReader(new FileReader(file));
        String a = "" ;
        String line;
        while ((line=br.readLine())!=null )
        {
            a+=line;
            System.out.println(a);

        }

        System.out.println("a= "+a);
        String b= compress(a);
        return b;
    }
    public String unCompress1(String path) throws IOException {
        this.path=path;
        File file =new  File(this.path);
        BufferedReader br=new BufferedReader(new FileReader(file));
        String a = "" ;
        String line;
        while ((line=br.readLine())!=null )
        {
            a+=line;
            System.out.println(a);

        }

        System.out.println("a= "+a);
        String b= un_compress(a);
        return b;
    }
    public void Save (String path,String text) throws IOException {
    FileWriter fileWriter=new FileWriter(path);
    fileWriter.write(text);
    fileWriter.close();
}

}


