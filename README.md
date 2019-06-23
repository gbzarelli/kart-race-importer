# Kart Race Importer [![CircleCI](https://circleci.com/gh/gbzarelli/kart-race-importer.svg?style=svg)](https://circleci.com/gh/gbzarelli/kart-race-importer)

O 'Kart Race Importer' é um projeto para importação de dados mantidos
em arquivos de Log. Esses arquivos contém informações de uma corrida
de kart, mantidas em um formato estrutural de 'voltas', aonde cada volta
contém informações do piloto, tempo, volta e velocidade. A ideia do
projeto e transcrever essas informações em dados complexos, que nos 
permitam visualizar informações estatísticas sobre a corrida.

## Dados para importação

A pasta dos dados a serem importados está configurada no arquivo
`application.yml` que fica armazenada no pasta resources do projeto
`(src/main/resources)`, todo arquivo de corrida deve seguir um padrão 
de nome para que a importação reconheça os arquivos, começando com ano, mês 
e dia tudo junto.

Segue um exemplo de arquivo a ser importado:

```text
Hora                               Piloto             Nº Volta   Tempo Volta       Velocidade média da volta
23:49:08.277      038 – F.MASSA                           1		1:02.852                        44,275
23:49:10.858      033 – R.BARRICHELLO                     1		1:04.352                        43,243
23:49:11.075      002 – K.RAIKKONEN                       1     1:04.108                        43,408
23:49:12.667      023 – M.WEBBER                          1		1:04.414                        43,202
23:49:30.976      015 – F.ALONSO                          1		1:18.456             			35,47
23:50:11.447      038 – F.MASSA                           2		1:03.170                        44,053
23:50:14.860      033 – R.BARRICHELLO                     2		1:04.002                        43,48
23:50:15.057      002 – K.RAIKKONEN                       2     1:03.982                        43,493
23:50:17.472      023 – M.WEBBER                          2		1:04.805                        42,941
23:50:37.987      015 – F.ALONSO                          2		1:07.011		             	41,528
23:51:14.216      038 – F.MASSA                           3		1:02.769                        44,334
23:51:18.576      033 – R.BARRICHELLO		              3		1:03.716                        43,675
23:51:19.044      002 – K.RAIKKONEN                       3		1:03.987                        43,49
23:51:21.759      023 – M.WEBBER                          3		1:04.287                        43,287
23:51:46.691      015 – F.ALONSO                          3		1:08.704			            40,504
23:52:01.796      011 – S.VETTEL                          1		3:31.315			            13,169
23:52:17.003      038 – F.MASS                            4		1:02.787                        44,321
23:52:22.586      033 – R.BARRICHELLO	    	          4		1:04.010                        43,474
23:52:22.120      002 – K.RAIKKONEN                       4		1:03.076                        44,118
23:52:25.975      023 – M.WEBBER                          4		1:04.216                        43,335
23:53:06.741      015 – F.ALONSO                          4		1:20.050			            34,763
23:53:39.660      011 – S.VETTEL                          2		1:37.864			            28,435
23:54:57.757      011 – S.VETTEL                          3		1:18.097			            35,633
```

## Importação

No modelo atual de importação, podemos passar uma data específica (Dia, Mês
e Ano) e o sistema irá buscar na pasta configurada as corridas daquela data
e retornar uma lista de corridas importadas.

## Modelo de importação

Ao importar o arquivo, um modelo de corrida será gerado, através desse 
modelo podemos extrair diversas informações como:

- Pilotos na corrida:
    - Todas as suas voltas.
    - Volta mais rápida.
    - Média de velocidade.

- Lista de voltas da corrida:
    - Numero da volta.
    - Classificação da volta por piloto:
        - Colocação na volta
        - Informação da volta:
            - Horário.
            - Tempo de volta.
            - Média de velocidade.
        - Lista com a diferença de tempo para cada piloto a sua frente na volta.
            - Piloto
            - Colocação na volta
            - Tempo
    - Piloto mais rápido da volta:
        - Colocação na volta
        - Tempo

( Outras estatísticas serão desenvolvidas posteriormente )

## Endpoint

Atualmente não criei um endpoint claro para esse projeto, como um REST ou algo
do gênero. Mas para execução, criei uma `Main.java` na raiz do package
para que possa ser feita a execução. A `Main` class cria uma instância
do `ImporterService` e executa o `showRacesFrom(date)`, o resultado é
convertido para JSON para verificarmos como se fosse uma API. 

```java
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.showRacesFrom(LocalDate.of(2019, Month.JUNE, 18));
    }

    private final ImporterFacade importer = new ImporterService();

    private void showRacesFrom(LocalDate localDate) {
        RacesImported races = importer.importRaces(new ImportRaceByDate(localDate));
        System.out.println(new Gson().toJson(races));
        System.out.println(races);
    }
}
```

## Nota

O projeto tem diversos pontos que poderia utilizar recursos como
injeção de dependências e bibliotecas que 
facilitariam o desenvolvimento, porém, o projeto foi desenvolvido com 
a idéia de utilizar os próprios recursos da linguagem com o mínimo 
possível de dependências externas.

## Licença
[MIT](https://choosealicense.com/licenses/mit/)
