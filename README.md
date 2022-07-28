# Challenge-Mockito-Fixture

## Exercícios
- Crie testes usando `@Mock` e `@Spy` para a classe `Executer`, utilize o Fixture-Factory para facilitar a criação de 
  objetos para testes. (utilize apenas o fixture para a criação de instâncias, mas se for um objeto vazio não é 
  necessário)
- No método `getAddressOf()` do `Executer`, faça ele retornar um endereço mesmo passando um objeto vazio para ele (objeto 
  vazio = `new DetailedPerson()`)
- No método `isRelative()` do `Executer`, faça os 2 cenários abaixo:
  - Se a string passada for `"Java"` ou `"JUnit"`, retorne `true`;
  - Se a string passada for qualquer outra string, retorne `false`;
- No método `printStrAndInt()` do `Executer`, teste se o método `detailedPersonUC.printStringAndInt()` foi chamado 
  duas vezes e cada parâmetro em cada chamada.
- No método `execute()` do `Executer`, faça o cenário de sucesso e de erro. 
    - Se a idade for menor que 18, faça o teste para assegurar que o `detailedPersonUC.printStringAndInt()` e 
      `detailedPersonUC.getPersonAddress()` não são chamados.
    - Teste se o método `printStringAndInt()` é chamado em cada caso e com os parâmetros corretos. 
    - Faça com que a variável `address` dentro do método `execute()` nunca fique vazia.
