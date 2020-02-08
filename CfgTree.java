
package cfgtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CfgTree {

    
    public static void main(String[] args) throws IOException {
        
        String[] names;
        List<String> nameslist = new ArrayList<>();
        List<NonTerminal> nonTerm = new ArrayList<NonTerminal>();
        String input;
        String[] row;
        String[] okSign;
        String[] split;
        
        
        
        BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("“|” karakteri ayraç , “-->” "
                + "karakteri sağa ok işareti , “,” karakteri satır ayracı olacak şekilde giriniz");  
        
        input = ob.readLine();
        ob.close();
        row=input.split(",");
        
        names = new String[row.length];
        for(int i=0; i<row.length;i++)
        {
            okSign=row[i].split("-->");
            names[i]=String.valueOf(okSign[0].charAt(0));
            
            nonTerm.add(new NonTerminal());
            
            List<String> trans=new ArrayList<String>();
            
            
            split=okSign[1].split("\\|");
            for(int j=0;j<split.length;j++)
            {
                
                trans.add(split[j]);
            }
            nonTerm.get(i).trans=trans;
            nonTerm.get(i).name=names[i];
            nameslist.add(names[i]);
        }
        
        CFG(nonTerm,nameslist);
       
    }
    
    
    
    public static void CFG(List<NonTerminal> nonTerm,List<String> nameslist) {
        List<String> kontrol=new ArrayList<String>();
        
        List<String> tekrar=new ArrayList<String>();
        
        boolean k=true;
        
        while(k)
        {
            k=false;
            
            for(String nt:nonTerm.get(0).trans)
            {
                
                for(String nt2:nameslist)
                {
                    if(nt.contains(nt2))
                    {
                        k=true;
                        break;
                    }
                     

                }
                
            }
          
            if(k==false)break;
            
            
            
            for(String a:nonTerm.get(0).trans)
            {
                boolean cont=false;
               
                for(int i=0;i<a.length();i++)
                {
                    if(nameslist.contains(String.valueOf(a.charAt(i))))
                    {
                        NonTerminal nont = null;
                        cont=true;
                        for(int j=0;j<nonTerm.size();j++)
                        {
                            
                            if(nonTerm.get(j).name.equals(String.valueOf(a.charAt(i))))
                            {
                                
                                nont=nonTerm.get(j);
                            }
                            
                        }
                        for(int j=0;j<nont.trans.size();j++)
                        {
                            String b=a.substring(0,i)+nont.trans.get(j)+a.substring(i+1);
                            if(kontrol.contains(b)==false)
                            {
                                kontrol.add(b);
                                
                            }
                            else 
                            {
                                tekrar.add(b);
                            }
                        }
                        
                        
                    }
                    
                    
                    
                }
               
                if(cont==false)
                {
                    if(kontrol.contains(a)==false)
                    {
                        kontrol.add(a);
                    }
                    else 
                    {
                        tekrar.add(a);
                    }
                }
                
            }
           //Collections.copy(nonTerm.get(0).trans, kontrol);
           List<String> kontrol1=new ArrayList<String>(kontrol);
            
            nonTerm.get(0).trans=kontrol1;
            
            kontrol.clear();
            
        }
        
        for(int i=0;i<nonTerm.get(0).trans.size();i++)
        {
            System.out.println(nonTerm.get(0).trans.get(i));
        }
        System.out.println("");
        for(int i=0;i<tekrar.size();i++)
        {
            System.out.println(tekrar.get(i));
            
        }
        
        
    }        

}
