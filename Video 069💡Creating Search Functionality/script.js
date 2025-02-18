// Here query means the text the user is entering in the search box.
const search=()=>
    {
        let query=$("#search-input").val();
        
        if(query == "")
        {
            $(".search-result").hide();
        }
        else
        {
            console.log(query);
            
            //sending request to server
            let url=`http://localhost:8080/search/${query}`;
            
            fetch(url)
            .then((response)=>{
                return response.json();
            })
            .then((data)=>
            {
                //data..
                
                let text=`<div class='list-group'> `;
                
                data.forEach((contact)=>{
                    text+=`<a href='/user/${contact.cId}/contact' class='list-group-item list-group-item-action'> ${contact.name} </a> `
                });
                
                
                text += `</div>`;
                
                $(".search-result").html(text);
                $(".search-result").show();
                
            });
            
            
            $(".serach-result").show();
        }
    };