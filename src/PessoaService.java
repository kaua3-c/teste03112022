import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import org.json.*;

import lombok.RequiredArgsConstructor;
import java.util.*;
// [ <-- cochetes é uma coleção de objetos

@RequiredArgsConstructor
public class PessoaService {
    private HttpClient Client = HttpClient.newHttpClient();
   
    private final String url;

    public List<Pessoa> listar(){
        List <Pessoa> pessoas = new ArrayList <> ();
    
   // public PessoaService (String url){
   // this.url = url;
   // }

    
        try{
            //design pattern: builder
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).build(); 
            var response = Client.send(request, BodyHandlers.ofString());
          //  System.out.println(response.body());
            JSONObject raiz = new JSONObject(response.body());
            JSONArray items = raiz.getJSONArray("items");
      //      System.out.println(items);
            // para pegar o valor de items usamos valor de referencia/indice

            for (int i =0; i< items.length(); i++)
            {



            JSONObject usuarioJSON =items.getJSONObject(i);
            System.out.println(usuarioJSON);
            String nome = usuarioJSON.getString("nome");
            int idade = usuarioJSON.getInt("idade");
            String hobby = usuarioJSON.getString("hobby");
            Pessoa p = new Pessoa();
            
            p.setNome(nome);
            p.setIdade(idade);
            p.setHobby(hobby);

            pessoas.add(p);
           
            System.out.println(p);
            System.out.println("******************************");
        }
            //System.out.printf("%s\n %d\n %s \n",nome, idade, hobby);


          //  JSONObject raiz = new JSONObject(response.body()); //abstração, para facilitar o "organização"
          //  JSONArray items = raiz.getJSONArray("items");
        //System.out.println (items);
        //JSONObject primeiro = items.getJSONObject(0);
          //  System.out.println(primeiro);
       // String nome = primeiro.getString("nome");
       // System.out.println(nome);
       
       
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return pessoas;
        }


    /*public static void main(String[] args) {
    System.out.printsssln(Client);
    }*/
}
