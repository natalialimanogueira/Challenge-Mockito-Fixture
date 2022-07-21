# Challenge-Mockito-Fixture

## Exercícios
- Crie testes usando `@Mock` e `@Spy` para a classe `Executer`, utilize o Fixture-Factory para facilitar a criação de 
  objetos para testes.
- No método `drinkAlcohol()` do `Executer`, faça 3 testes:
  - 1º: Com a idade maior que 18;
  - 2º: Com a idade menor que 18;
  - 3º: Com a idade maior que 18, porém a exceção deve ser jogada mesmo assim.
- No método `getAddressOf()` do `Executer`, faça ele retornar um endereço mesmo passando um objeto vazio para ele (objeto 
  vazio = `new DetailedPerson()`)
- No método `isRelative()` do `Executer`, faça os 2 cenários abaixo:
  - Se a string passada for `"Java"` ou `"JUnit"`, retorne `true`;
  - Se a string passada for qualquer outra string, retorne `false`;
