# Domain-Driven Design (Vaughn Vernon)

## Chapter 1. DDD for Me

Cenários em que DDD pode ajudar:

- Software é considerado uma despesa (deveria ser considerado como sendo a estratégia)
- A preocupação dos desenvolvedores é tecnologia (deveria ser o negócio)
- Banco de dados é prioridade (o sistema deveria ser agnostico em relação a isso)
- Nomes de classes e metódos não tem relação alguma com o negócio.
- Problemas anteriores estão relacionados a priorizações erradas. Exemplo: O dono do negócio priorizou muito esforço numa funcionalidade que ninguém usa (e não dá retorno financeiro algum)
- As estimativas do projeto são geralmente altas
- As lógicas de negócio estão na interface gráfica ou na persistencia de dados. Acontece também de ter persistencia no meio da lógica de negócios.
- Há inconsistencias no banco de dados que não permitem que consultas importantes sejam feitas em tempos satisfatórios.
- Abstrações equivocadas: Os programadores tentam prever demais o futuro e deixam de fazer o que o negócio exige no momento atual.
- Serviços fortemente acoplados: `Serviço A` chama `Serviço B` que chama o `Serviço C`...

As causas acima geralmente surgem do "Não vamos perder tempo com design nesse momento"

> "Software is eating the world"

"Não vamos perder tempo com design" é uma falácia, pois quando não se faz design, a opção, na verdade, foi por um design ruim.

Se você tem um time com 5 programadores e nenhum design foi definido em conjunto, o que vai acontecer é que o software será um emaranhado de 5 designs.

> Design bom é diferencial competitivo.

### Design estratégico

Antes de entrar nos próximos tópicos: 

Linguagem ubíqua é quando todos no time (ou squad se achar melhor) falam a mesma lingua. Se eu disser por exemplo "Dados sensíveis", esse termo tem que ser comum a todos do time, e inclusive, o mesmo termo deve aparecer no código do software (no caso do software geralmente escrevemos em ingles. Nesse caso, tradução direta)

Exemplo: "É importante para o negócio termos uma api para pegar o último cliente que foi inserido no sistema". No código dever existir algo como getLastCustomerInserted.

- Bounded context (Para segregar domínios)
- Desenvolvimento de linguagem ubíqua como modelo de domínio (para cada bounded context)
- Linguagem ubíqua deve ser feita junto com pessoas que conhecem do negócio.
- Subdomains (ajuda a lidar com complexidade de sistemas legados )
- Integração de bounded contexts (Context maps)

### Tatical Design

- Aggregate patter (Unir entities + value objects).

Observação sobre entity e value objects.

Exemplo:

```
Customer (name, rg, address)
Address (rua, numero, bairro, cidade, cep)
```

`Customer` é uma **Entity**. Suponha que o customer seja a Fulana da silva. Se a Fulana da silva se casa, ela passa a se chamar Fulana da Silva dos Santos. Perceba que o nome mudou, mas ela continua sendo a mesma pessoa.

`Address` é um **value object**. Suponha que o endereço seja "Rua dos bobos, 0". Se mudarmos somente um dos atributos, o número por exemplo, o endereço é outro.

A Customer "Fulana da silva" **é** a, agora "Fulana da Silva dos Santos"
"Rua dos bobos, 0" **não é** "Rua dos bobos, 1"

## Chapter 2. Strategic Design with Bounded Contexts and the Ubiquitous Language

Em resumo, DDD é primordialmente voltado à modelagem de uma linguagem ubíqua em um determinado contexto delimitado (Bounded context).

Bounded context é um contexto delimitado semanticamente. Não é um módulo do sistema ou uma biblioteca.

Um exemplo: No contexto de um sistema de vendas, o cliente é a pessoa para quem estou vendendo. Num sistema de compras, o cliente sou eu. Dependendo do contexto o cliente tem um significado semantico totalmente diferente.

Um Bounded Context é onde o modelo é implementado, e para cada Bounded Context existirá um artefato de software.

**Problem Space**: É onde as estratégias são pensadas em alto nível, isto é, sem nenhum tipo de implementação.

**Solution Space**: Aqui é a implementação do que foi discutido no Problem Space.

**Core Domain**: Implementação de um Bounded Context que é considerado a estratégia chave para o negócio.

O **Core Domain** precisa ser desenvolvido com o intúito de diferenciar, competitivamente falando, o seu negócio do negócio dos concorrentes.

Exemplos:

- Bidu: Core Domain: Venda de seguro. Tudo o que está em volta dá suporte a isso, exemplos: Marketing, Cotação online, Serviço de CEPs, Tabela de veículos ...
  - A empresa que vende seguro da melhor forma, está a frente.

- Bazar: Core Domain: Compra de consórcios. É com isso que as contas são pagas. Calculo de preços, validação de documentos, Gerenciamento de contratos, análise de dados e marketing ajudam o Core Domain.
  - A empresa que melhor compra consórcios está a frente.

Outro exemplo interessante (não está no livro):

E-commerce: Core Domain: Vender produtos. Para ajudar nessa tarefa temos, entre outras coisas, um bonded context relacionado aos fretes. Mas fretes não é o core domain.

Transportadora: Core Domain: Vender Fretes

A proposta principal do DDD: Sua empresa não pode (e nem deve tentar) fazer tudo. Deve-se escolher aquilo o que é mais importante para gastar energia. A grosso modo, o resto, terceriza.

Sobre a linguagem usada em cada context bounded:

Imagine que cada context bounded seja um país. No contexto Brazil, portugues; No contexto Alemanha, Alemão, No contexto USA, inglês.

Um exemplo mais prático: Compra de consorcio (Cliente, oferta, dados da quota), Marketing(CPC, CPA, Lead Organico, Lead Pago)

Obs: Perceba que Cliente e Lead, quando **NÃO** pensados em contextos delimitados podem acabar se transformando na mesma coisa. Isso é comum quando se trabalha orientado a banco de dados, e não orientado ao negócio.

### Bounded Contexts, Teams and Source Code Repositories

Idealmente cada Bounded Context deve ser mantido por um time. Também é recomendado que cada bouded context tenha um repositório e um banco de dados próprio.

Uma grande razão para o uso de Bounded Context é que chega um momento em que acontece um amontoado tão grande de novos conceitos que a manutenção dessa bagunça se torna muito difícil.

Novamente aquele exemplo de compra e venda: No mesmo contexto tenho venda para o cliente e venda do fornecedor para mim. Venda nesse caso se torna ambíguo.

Quando o sistema chega a um ponto em que tudo é uma bagunça (sem contextos definidos) *o que geralmente acontece é a produção de um novo software que é uma "Big Ball of Mud"*. Isso geralmente é um monolito ruim. Terá um monte de models sem contexto definido. Aí a bola de lama está a solta.

No livro tem um exemplo de um mesmo termo que quando usado em bounded context diferente tem sentidos totalmente diferentes.

Numa seguradora, o termo policy pode ser encontrado no contexto de **underwriten** (politica de cálculo de risco); **inspections** (política de inspeção do bem) e **claims** (politica de ativação de sinistro). Se tentarmos unificar tudo em Policy você certamente terá problemas.

>Dica: Não nomear classes como UnderwritenPolicy, InspectionPolicy e ClaimPolicy. O Bounded context se encarrega disso. O nome deve ser simplesmente Policy.

Na industria aeronáutica o termo flight pode significar diferentes coisas em diferentes contextos.

Flight pode ser um voo de um lugar a outro.
Flight pode ser uma etapa da manutenção de um avião.
Flight pode ser um termo do ticket do passageiro (flight nonstop e flight one-stop)

No livro tem um caso de estudo sobre o planejamento de um "trello" sem delimitações de contexto. Não dá pra por aqui porque tem muitas imagens. Porém ficam aqui alguns termos levantados no "planejamento":

- Product
  - BacklogItem
    - Task
      - EstimationLogEntry
  - Release
    - ScheduledBacklogItem
  -Sprint
  - CommittedBacklogItem

- Forum
  - Discussion
    - Post

- Calendar
  - CalendarEntry

- Account
  - SupportPlan
    - Incident

- Team
  - Product Owner
  - Team member

  - Payment
- Tenant
  - User
    - Permission

E mais um monte de termos

Agora, olhando todos os termos acima a questão é: Qual é o core desse software?

Um primeiro passo é perguntar qual dessas palavras faz parte da linguagem ubiqua do scrum (supondo que o "Trello" é voltado ao scrum)

Por exemplo: "Tenant", "User" e "Permission" não tem nada a ver com Scrum. Mas se ao invés desses termos usarmos "ProductOwner" e "TeamMember" (no lugar de User dentro de um Tenancy) aí temos uma linguagem ubíqua ao scrum.

"SupportPlans" e "Payments". Esses, de fato, não tem nada relacionado. Então, não fazem parte do core.

Depois de várias análises parecidas com os exemplos acima chegou-se ao core do scrum:


- Product
  - BacklogItem
    - Task
      - Volunteer
      - EstimationLogEntry
  - Release
    - ScheduledBacklogItem
  - Sprint
    - CommittedBacklogItem
- Team
  - ProductOwner
  - TeamMember

- Discussion

Sobre "Discussion": Isso gerou outro bounded context:

- Forum
  - Discussion
    - Post
- Calendar
  - Calendar entry

Existe também um bounded context sobre a conta de usuário/empresa:

- Account
  - Payment

Outro bouded context é o de suporte:

- SuportPlan
  - Incidents

Mas nem só de substantivos o core domain é feito. É interessante descreve o core domain a partir de cenários. Exemplo:

O **PRODUCT OWNER COMMITS** um **BACKLOG ITEM** na **SPRINT**.
O **BACKLOG ITEM** pode ser **COMMITTED** somente se já existe um **SCHEDULE** para **RELEASE**, e um **QUORUM** de **TEAM MEMBERS** tem aprovado. Se o **BACKLOG ITEM** já está **COMMITED** em outro **SPRINT**, este deve ser **UNCOMMITTED** primeiro. Quando o **COMMIT** estiver pronto, notifique a **SPRINT** onde o **BACKLOG ITEM** estava **COMMITED** e a **SPRINT** atual.

A partir do exemplo acima já é possível escrever alguns testes de aceitação para por a domínio a prova.

> Um aviso: Encontrar os verbos, usar cenários, figura e diagramas ajudam a desenhar o modelo. Mas o modelo, no fim das contas deve ser o código. O código deverá ser o modelo.

> “In the end the code is the model and the model is the code”

Arquiteturas onde o domain model pode ser usado

- Port and adapters (arquitetura hexagonal)
- Event-Driven architecture (Event sourcing)
- CQRS (Command Query Responsability Segregation)
- REST
- SOA

> Sobre conversars entre desenvolvedor e pessoas de negócio. Deve-se evitar comunicações via documento. Deve-se dar preferencia por conversas.

## Chapter 3. Strategic Design with Subdomains

Quando se trabalha com DDD sempre temos um Bounded Context principal (o core domain) e outros subdomínios.

Relembrando o core domain e os subdominios lá do capitulo 2

- Core (Agile Project Management Core)
  - Product
    - BacklogItem
      - Task
        - Volunteer
        - EstimationLogEntry
    - Release
      - ScheduledBacklogItem
    - Sprint
      - CommittedBacklogItem
  - Discussion
  - Team
    - ProductOwner
    - TeamMember

- Subdomain 1
  - Tenant
    - User
    - Role

- Subdomain 2
  - Account
    - Payment

- Subdomain 3
  - SupportPlan
    - Incident

- Subdomain 4
  - Forum
    - Discussion
      - Post
  - Calendar
    - CalendarEntry

- Subdomain 5
  - ResourceManager
    - TimeConsumingResource
      - Schedule
      - Availability


Existem três tipos de subdomínios:
  - **Core Domain** - Distingue seu negócio dos seus concorrentes.
  - **Supporting Subdomain** - Domínio de suporte - O core domain depende dos domínios de suporte. Esses domínios de suporte não são softwares de prateleira. Eles precisam ser desenvolvidos, mas esse é o caso em que o desenvolvimento pode ser terceirizado
  - **Generic Subdomain**. Os domínios genéricos são aqueles que podem ser desenvolvidos in house, mas geralmente podem ser encontrados prontos. Docusign, por exemplo.

## Chapter 4. Strategic Design with Context Mapping

**Context Mapping** é a integração entre *Core Domain*, *Suport Domain* e *Generic Domain*

Os bounded context tem uma representação gráfica (uma elipse). A integração entre os bounded context são realizadas através de uma linha que liga suas representações.

### Tipos de Mappings

- **Partnership**. As equipes possuem dependencias mútuas.
Exemplo: A equipe do "software legado" e a equipe "software novo". Toda mudança reflete nos dos contextos (velho e novo). Nesse caso tem que haver integração contínua para as coisas se mantenham de pé. Na prática, não é uma relação sustentável a longo prazo.
Representação (linha grossa entre as elipses que representam cada contexto)


- **Shared Kernel**. Contexto compartilhado com outros contextos. Não pode ser alterado sem consultar antes todos os times que dependem dele. Numa empresa que trabalhei existia um Projeto que se chamava Bifrost (referencia à ponte que liga os mundos do Thor). Isso era um contexto compartilhado. Minha opinião: Horrível.
Representação (uma intersecção - Tipo deiagrama de venn)

- **Customer-Supplier** - o fornecedor é o *upstream* e o cliente é o *downstream*. O fornecedor que fornece api's. Os times devem ter testes de contrato para garantir que a equipe upstream possa trabalhar sem medo de mudanças. Representação (uma linha com U ou D nas pontas - U é upstream, D é downstream)

- **Conformist** - O seu bounded context é o downstream e o upstream é, por exemplo, o mercado livre. O mercado livre não vai falar sua lingua. Se conforme e faça uma camada de *Anti Corruption Layer* 

- **Anticorruption layer** - Numa corretora de seguros existe uma linguagem ubiqua e as seguradoras, por sua vez, tem as suas. Numa integração entre corretora e seguradora a relação é de conformismo. E para isso, a corretora (que é downstream) usa uma *Anticorruption layer* para traduzir a liguagem da seguradora a linguagem da corretora.

- **Open Host Service** - Seria a idéia do Open Bank. API's em que a linguagem ubiqua fazem sentido no meu negócio. PIX no open bank vai ser PIX no meu sistema.

- **Published Language** - Parece ser uma API. Não ficou muito claro.

- **Separate Ways** - Pelo o que entendi: é aquele programinha aleatório que resolve um negócio bem pontual e não tem muita relação com o negócio. Um programinha para inventariar os bens de uma empresa. Isso não tem que estar relacionado ao negócio.

- **Big Ball of Mud**. Onde todo tipo de contexto está misturado. Para mexer em uma coisa há um risco enorme de estragar outra.

Essa referencia é mais clara do que o livro: https://www.eduardopires.net.br/2016/03/ddd-bounded-context/

> “Making Good Use of Context Mapping”

Esse subtopico é sobre como integrar contextos

> **EVITE** integrações via banco de dados. .

Os tipos de integração. Do menos robusto para o mais robusto.

- RPC (Remote Procedure Call) (Exemplo: XML via SOAP)
  - Se a rede estiver zuada, ferrou.

- RESTfull HTTP
  - Se a rede estiver zuada, ferrou.
  Uma observação sobre o REST. Geralmente cometemos o erro de desenhar o payload de acordo com o nosso model. Se fizermos isso, o payload não será estável e por consequência os clientes da api terão que entrar numa relação de conformismo. "Se os donos do serviço ficam mudando o formato do payload toda hora, a gente que lute pra se adaptar". Usar DTO é uma boa estratégia para manter a consistencia.

- Messaging
    
    É o meio mais robusto. Em parte é porque o desenho já prevê lentidão e falha de um dos lados da relação. Assim, ao contrário do REST, por exemplo, se houver problemas na rede a mensagem será entregue da mesma foram. Vai demorar, mas será entregue.

Na integração com mensagens o publisher publica um **Domain Event** e um ou mais subscribers consomem esse evento. Isso gera, ou a criação ou update de agreados nos clientes.

Obs: Usando polling é possível ser asyncrono usando REST.


> **EVITE** Train Wrecks.

A chama B de forma sincrona e B chama C também de forma sincrona.

Sobre integração via mensagens: É muito importante que o serviço consumidor seja idempotente (se receber várias mensagens repetidas, o resultado tem que ser sempre o mesmo)

### Exemplo em Context Mapping

Para esse exemplo iriemos usar um caso já citado

- Underwriting Context
  - Policy
- Claims Context
  - Policy
- Inspections Context
  - Policy


Supondo que o core domain é o Supplier (Fornecedor) é o Underwriting Context:
Nesse caso , quando a policy é criada, um *domain event* (PolicyIssued) é enviado para uma mensageria que será consumida por Claims Contex e Inspections Context. O evento (PolicyIssued) só contém o dado `policyId`. Os contextos Claims e Inspections vão criar suas policies e nelas terá o atributo `issuedPolicyId`.

Se houver a necessidade de mais informações os contextos downstrean (Claim e Inspection) pedem ao upstream (Underwritin).

### Enrichment versus Query-Back Trade-offs

Quando se usa enrichment o Domain Event já vai com toda informação necessária para o cliente. Esse caso é bom para quando se busca autonomia nos clientes.

No caso do Query-Back o domain event vai só com o id. A vantagem é que não a preocupação de entender a necessidade de cada cliente. Os clientes que peçam o que faltou, quando for necessário.

As vezes um balanceamento entre os dois casos é o ideal.

## Chapter 5. Tactical Design with Aggregates

Nos capítulos anteriores falou-se principamente sobre bounded contexts, como nomeá-los (Core Domain, Generic Domain, Support Domain) e como definir a relação entre eles (Context map). Este capítulo aborda a parte interna de um bounded context.

O que é um aggregate?

O aggregate é um composto de entities e value objects.

### Entity:

Pense na entity person. "Maria da Silva" é uma entidade do tipo person. Os atributos de Maria podem até mudar, mas ela continua sendo a Maria. O nome, por exemplo, pode mudar para Maria da Silva dos Santos (após um casamento, por exemplo), mas apesar do nome diferente, continuamos falando da mesma pessoa.

### Value Object

Pense na classe a seguir:

```java
class Endereco {
  public Endereco(rua, numero, bairro, cidade, cep) {
    // código do construtor
  }
}
```

Nesse caso, se mudarmos qualquer atributo, o endereço passa a ser outro totalmente diferente.

Uma observação: Aggregate não é o aggregation do UML.

A definição do Martin Folwer: https://martinfowler.com/bliki/DDD_Aggregate.html

Aggregates são grupos de objetos que sempre precisam ser consistentes. Portanto, esse grupo deve ser criado ou atualizado dentro de uma transação.

Fiz [aqui](https://github.com/helioalb/DDD/blob/main/aggregate.persistence.example/src/test/java/me/helioalbano/aggregate/persistence/usecases/OrderPersistenceTest.java#L25-L54) um exemplo de persistencia de um aggreate.

Exemplo de *root entity*: Na relação do [exemplo acima](https://github.com/helioalb/DDD/blob/main/aggregate.persistence.example/src/test/java/me/helioalbano/aggregate/persistence/usecases/OrderPersistenceTest.java#L25-L54) existe as entities:

- Order (Entity)
  - OrderItems (Entity)
    - Product (Entity)
        - price (Value object)

Nesse caso, Order é um *root entity*

Voltando ao conceito de *transaction*: Somente o estado completo da Order faz sentido.

> Dois aggregates não podem estar sob a mesma transação.
### Aggregate Rules of Thumb

1. Proteja negócios invariantes dentro de *Aggregate boundaries*
2. Desenhe pequenos *Aggregates* 
3. Referencie outros *Aggregates* somente por identidade.
4. Atualize outros *Aggregates* usando consistencia eventual.

#### Rule 1: Protect Business Invariants inside Aggregate Boundaries

Quem determina a composição do aggregate é o negócio. Uma *Order* por exemplo tem que ser salva com todos seus *OrderItems*.

#### Rule 2: Design Small Aggregates

No livro tem um exemplo de aggregate que é: **Product** tem muitos **BacklogItem**, muitas **Release** e muitos **Sprint**. Com o tempo isso pode crescer demais. Isso não é um aggregate eficiente.

Uma boa solução é quebrar esse grande aggregate em quatro menores: **Product Aggregate**, **BacklogItem Aggregate**, **Release Aggregate** e **Sprint Aggregate**.

As vantagens de pequenos aggregates são:
- Carregam rapidamente
- Usam menos memória
- Tornam o *garbage collector* mais eficiente.
- (talvez o mais importante) a taxa de sucesso nas transações será bem maior (comparado a um grande aggregate)
- São mais fáceis de trabalhar (depende de um único programador)
- São mais fáceis de testar
- Provavelmente estará seguindo o **SRP**.

Sobre o **SRP** (*Single Responsability Principle*): No caso do grande aggregate citado anteriormente, faça a você mesmo a pergunta:

> Qual é a razão para mudar o **Product**: É para ter um **Scrum product** melhor ou é para gerenciar *backlog items*, *releases* e *sprints*?

#### Rule 3: Reference Other Aggregates by Identity Only

No [exemplo de persistencia de um aggregate](https://github.com/helioalb/DDD/blob/main/aggregate.persistence.example/src/test/java/me/helioalbano/aggregate/persistence/usecases/OrderPersistenceTest.java#L25-L54) esse princípio não foi usado. *Product* por exemplo é carregado completamente em cada *OrderItem*. Para um *JSON-based store* isto é ok, mas para um banco relacional, por exemplo, isso é ruim. Já, o princípio de sempre usar o identificador, serve para qualquer mecanismo de armazenamento.

#### Rule 4: Update Other Aggregates Using Eventual Consistency

Exemplo: Imagine que um **BacklogItem** foi adicionado a um **Sprint**. Na transaction do **BacklogItem** um *Domain Event*, chamado *BacklogItemCommitted* é publicado. Quando o subscriber do *BacklogItemCommitted* o "vê" na fila, uma nova transaction é iniciada e o **Sprint** passa a ter o novo *BacklogItemId*.

## Modeling Aggregates

- Evite domínios anemicos (a menos que esteja trabalhando com linguagens funcionais)

- Valores não devem ser setados diretamente. A classe deve alterá-los (get publico e set privado)

- Faça as abstrações com bastante cuidado. Isto é, sua abstração deve refletir a linguagem ubíqua. Nada de tentar ser genérico de mais. Ao invés de criar um ScrumElement para representar BacklogItem ou Release, Crie Scrum, BacklogItem e Release. 

## Right-Sizing Aggregates
 1. Foque na segunda regra *"Design small Aggregates."* Começe cirando todo aggregate com apenas uma *entity*. Popule os attributos da *entity*. Para os atributos: Pense naquilo que é usado para identificar e encontrar a sua entity.
2. Agora foque na segunda regra *"Protect business invariants inside Aggregate boundaries"*. Descubra o que deve mudar (em outros aggregates) se houver um update no aggregate atual.
3. Descubra em quanto tempo após o update do aggregate atual os outros aggregates precisam ser atualizados (imediatamente, 1 segundo, 1 minuto, 1 hora, ...)
4. Se a responsa ao 3. foi "imediatamente", é recomendável compor o seu aggregate.
5. Os aggregate restantes (aqueles que não fizeram parte da composição) podem ser atualizados com *consistencia eventual*.

### Testable Units

O aggregate deve ser fácil de testar unitariamente (unitário é diferente de teste de aceitação)

## Chapter 6. Tactical Design with Domain Events

**Domain Event** é um registro de alguma operação importante dentro de um *Bounded context*.

**causal consistency** Um *busines domain* resulta em *causal consistency* se suas operações, casualmente relacionadas - um operação causa a outra - são vistas por cada node dependente na mesma ordem.

*Causal consistency* pode ser alcançada através da criação e publicação de correta order de *Domain event*.

### Designing, Implementing, and Using Domain Events

```java
public interface DomainEvent {
  public Date occurredOn();
}
```

O exemplo acima é o mínimo que um *Domain Event* deve suportar.

O nome do *Domain Event* deve ser bem escolhido, baseado na linguagem ubíqua do modelo. Esses nomes são a ponte entre o domínio e o mundo externo.

Exemplos:

- ProductCreated
- SprintScheduled
- ReleaseScheduled
- BacklogItemPlanned
- BacklogItemCommited

O nome do *Domain event* deve ser o de uma ação que ocorreu no passado, isto é, um verbo no passado (ProductCreated, SprintScheduled...)

Sobre as propriedades que devem estar contidas no *Domain Event*, pergunte a si mesmo: "Qual é a motivação da aplicação que faz com que o *Domain Event* seja publicado?". No caso do `ProductCreated`, por exemplo, existe um comando que causa isso. O comando é chamado `CreateProduct` e o `ProductCreated` é o resultado.

O *Domain Event* deve ter todas as propriedades essenciais que foram fornecidas ao comando que criou o *Domain Event*.

O comando `CreateProduct`, por exemplo, recebeu: `tenantId`, `productId`, `name`(nome do produto) e `description`(descrição do produto). O `ProductCreated`, por sua vez, deve ser:

```java
class ProductCreated implements DomainEvent {
  private Integer tenantId;
  private Integer productId;
  private String name;
  private String description;

  public Date ocurredOn() {}
}
```

Alguns exemplos de *Domain Events* relacionados o *Agile Project management Context* (Exemplo base do livro):

```java
class ProductCreated {
  tenantId;
  productId;
  name;
  description;
}
```

```java
class SprintScheduled {
  tenantId;
  sprintId;
  productId;
  name;
  description;
  startsOn;
  endsOn;
}
```

```java
class ReleaseScheduled {
  tenantId;
  releaseId;
  productId;
  name;
  description;
  targetDate;
}
```

```java
class BacklogItemPlanned {
  tenantId;
  backlogItemId;
  productId;
  sprintId;
  story;
  summary;
}
```

```java
class BacklogItemCommited {
  tenantId;
  backlogItemId;
  sprintId;
}
```

### Como deve ser o processo de disparo do domain event.

O ideal é que na mesma transação sejam salvos o *aggregate* e o *Domain Event* (o domain event numa *store events*). Depois de tudo salvo, o *Domain Event* é disparado.

### Como garantir *causal order*?

É, também, de responsabilidade dos consumidores intepretar os *Domain Events* para que haja *causal order* .

### Não somente command.

A fonte de um *Domain Event* não é somente um *command* . Um fonte possível é a chegada de um determinado horário. Na *Wall Street*, por exemplo, as negociações terminam às 16:00 hr. Esse horário é conhecido como "Markets Closed". Nesse caso, baseado na linguagem ubíqua já temos o nome do *Domain Event": *MarketsClosed* . Esse *Domain Event* será "disparado" todos os dias às 16:00.

### **Event Sourcing**

*Event Sourcing* pode ser descrito como sendo a persistencia de todos os *Domain Events* que já tenham ocorrido para um *aggregate* . O estado atual de uma instancia de *aggregate* é obtido através da "reconstituição" de todos os *Domain Events* passados.

Todos os *Domain Events* que ocorreram para uma instancia de *aggregate*, ordenados pelo momento em que aconteceram, constituem o *event stream* desse *aggregate*.

Exemplo de como seria o stream numa tabela chamada Event Store:

Stream id|Stream Version|Event Type|Event Content
--|--|--|--
backlogItem123|1|BacklogItemPlanned|{...}
backlogItem123|2|BacklogItemStoryDefined|{...}
backlogItem123|3|BacklogItemCommited|{...}
...|N|....|{...}
...|N|....|{...}

Como a tabela *event store* é *append only*, o armazenamento de informações é rápido.

#### Sobre performance

Será necessário usar estratégias de cache ou snapshots. (O livro cita outro livro que cobre o assunto)


