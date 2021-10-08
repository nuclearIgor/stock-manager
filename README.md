# StockManagerAPI



## Descrição
StockManager é uma api restfull cujo propósito é manter registros de produtos, lançamentos de entradas e saídas,  busca de relatórios por data do lançamento ou pelo nome do produto.

##### tecnologias utilizadas:
Java Spring boot
Hibernate
Postgres
Docker

## Instalação
git clone:
cd folder
docker-compose up 


## Endpoints:
### Produtos:

#### GET: localhost:8080/products/all
retorna uma lista contendo todos os produtos registrados:

    [
      {
        "id": 1,
        "productName": "abacate",
        "stock": 25,
        "inDate": "2021-10-08",
        "outDate": null
      },
      {
        "id": 2,
        "productName": "banana",
        "stock": 800,
        "inDate": "2021-10-08",
        "outDate": null
      },
      {
        "id": 3,
        "productName": "item",
        "stock": 1,
        "inDate": "2021-10-08",
        "outDate": null
      }
    ]

#### GET: localhost:8080/products/find/:id
retorna o produto com id correspondente a pesquisa

    {
      "id": 1,
      "productName": "banana",
      "stock": 25,
      "inDate": "2021-10-08",
      "outDate": null
    }
caso não haja nenhum produto com o id pesquisado, retorna status 404/Not_found


#### GET: localhost:8080/products/name/:name
retorna o produto com nome correspondente a pesquisa

    {
      "id": 2,
      "productName": "abacate",
      "stock": 800,
      "inDate": "2021-10-08",
      "outDate": null
    }

caso não haja nenhum produto com o nome pesquisado, retorna status 404/Not_found
 
#### POST: localhost:8080/products/new
Nesse endpoint é feito o registro de um novo produto com nome e estoque, a data de entrada "inDate" é configurada automaticamente com a data atual.

Corpo da requisição:

    {
      "productName": "Produto",
      "stock": 100
    }
Reposta:

    {
      "id": 5,
      "productName": "Produto",
      "stock": 100,
      "inDate": "2021-10-08",
      "outDate": null
    }
#### PUT: localhost:8080/products/update/:id
Com base no tipo do lançamento ("entryType"),  a quantia em estoque do produto é atualizada e é setada a data da última entrada ("inDate") ou da última saída ("outDate")

Corpo da requisição:
 

    {
    	"productId": 5,
        "quantity": 10,
    	"entryType": "saída",
    	"productName": "Produto"
    }

Resposta:

    {
      "id": 5,
      "productName": "Produto",
      "stock": 90,
      "inDate": "2021-10-08",
      "outDate": "2021-10-08"
    }
Caso o usuário tente retirar uma quantia maior que a quantia em estoque, a requisição não é processada e o retorno é um erro 400/Bad_request

Corpo da requisição:

    {
    	"productId": 5,
        "quantity": 91,
    	"entryType": "saída",
    	"productName": "Produto"
    }

Resposta:

    {
      "timestamp": "2021-10-08T20:17:50.908+00:00",
      "status": 400,
      "error": "Bad Request",
      "path": "/products/update/5"
    }

#### DELETE: localhost:8080/products/delete/:id
Excluir o produto com id correspondente e retorna status 204/No_content

### Lançamentos:

#### GET: localhost:8080/entry/date/:date
Requisiçao:
##### localhost:8080/entry/date/2021-10-08

Resposta:

    [
      {
        "id": 1,
        "entryType": "entrada",
        "date": "2021-10-08",
        "quantity": 25,
        "productId": 1,
        "productName": "abacate"
      },
      {
        "id": 2,
        "entryType": "entrada",
        "date": "2021-10-08",
        "quantity": 800,
        "productId": 2,
        "productName": "banana"
      },
      {
        "id": 3,
        "entryType": "entrada",
        "date": "2021-10-08",
        "quantity": 1,
        "productId": 3,
        "productName": "item"
      },
      {
        "id": 4,
        "entryType": "saída",
        "date": "2021-10-08",
        "quantity": 5,
        "productId": 1,
        "productName": "abacate"
      },
      {
        "id": 5,
        "entryType": "saída",
        "date": "2021-10-08",
        "quantity": 5,
        "productId": 2,
        "productName": "banana"
      }
    ]

#### GET: localhost:8080/entry/name/:name
Requisição:
##### localhost:8080/entry/name/abacate

Resposta:

    [
      {
        "id": 6,
        "entryType": "entrada",
        "date": "2021-10-08",
        "quantity": 100,
        "productId": 4,
        "productName": "abacate"
      },
      {
        "id": 7,
        "entryType": "saída",
        "date": "2021-10-08",
        "quantity": 5,
        "productId": 4,
        "productName": "abacate"
      },
      {
        "id": 8,
        "entryType": "entrada",
        "date": "2021-10-08",
        "quantity": 40,
        "productId": 4,
        "productName": "abacate"
      },
      {
        "id": 9,
        "entryType": "saída",
        "date": "2021-10-08",
        "quantity": 50,
        "productId": 4,
        "productName": "abacate"
      }
    ]

