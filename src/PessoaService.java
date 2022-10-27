import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PessoaService {
    private HttpClient Client = HttpClient.newHttpClient();
    private final String url;

   // public PessoaService (String url){
   // this.url = url;
   // }

    public void listar(){
        try{
            //design pattern: builder
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).build(); 
            var response = Client.send(request, BodyHandlers.ofString());
            System.out.println(response.body());
            
            JSONObject raiz = new JSONObject(response.body()); //abstração, para facilitar o "organização"
            JSONArray items = raiz.getJSONArray("items");
        System.out.println (items);
        JSONObject primeiro = items.getJSONObject(0);
            System.out.println(primeiro);
       String nome = primeiro.getString("nome");
       System.out.println(nome);
       
       
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    /*public static void main(String[] args) {
    System.out.printsssln(Client);
    }*/
}
