# dynamicquery

this project is for dynamic query.it filter data from some more data according by criterion.it's principle is java8's filter api.  
now it support some data type and operator as listed below,if you want to expend it,just do it.

1. EQUAL
    1. STRING  
    1. LONG  
    1. DOUBLE  
    1. DATE   
1. LIKE  
    1. STRING  
1. LESSEQUAL
    1. LONG  
    1. DOUBLE  
    1. DATE  
1. GREATEREQUAL
    1. LONG  
    1. DOUBLE  
    1. DATE  
1. IN
    1. STRING  
    1. LONG  
    1. DOUBLE  
1. BETWEEN  
    1. DATE 
    1. LONG

if you want to use http request to do filter,here is some sample.

1. A && B
    ```
    curl -v -X GET  -H "Cookie:JSESSIONID=9465BC28E76EDC99763F35067B594141" -H "Content-Type:application/json"  -d '
       {
         "criterionList": [
           {
             "compareType": "EQUAL",
             "dataType": "STRING",
             "property": "name",
             "value": "name1"
           },
           {
             "compareType": "BETWEEN",
             "dataType": "DATE",
             "property": "createDate",
             "values": ["2018-09-01","2018-12-30"]
           }
         ],
         "operatorType": "AND"
       }
       ' 'http://localhost:8080/xxxx'
    ```    
    
1. A || B
    ```
    curl -v -X GET  -H "Cookie:JSESSIONID=9465BC28E76EDC99763F35067B594141" -H "Content-Type:application/json"  -d '
       {
         "criterionList": [
           {
             "compareType": "EQUAL",
             "dataType": "STRING",
             "property": "name",
             "value": "name1"
           },
           {
             "compareType": "BETWEEN",
             "dataType": "DATE",
             "property": "createDate",
             "values": ["2018-09-01","2018-12-30"]
           }
         ],
         "operatorType": "OR"
       }
       ' 'http://localhost:8080/xxx'
    ```  
    
1. A && (B || C)  
    ```
    curl -v -X GET  -H "Cookie:JSESSIONID=9465BC28E76EDC99763F35067B594141" -H "Content-Type:application/json"  -d '
    {
      "criterionList": [
        {
          "compareType": "LIKE",
          "dataType": "STRING",
          "property": "name",
          "value": "name1"
        },
        {
            "operatorType": "OR",
            "criterionList": [
              {
                "compareType": "LESSEQUAL",
                "dataType": "DATE",
                "property": "createDate",
                "value": "2018-01-01"
              },
              {
                "compareType": "GREATEREQUAL",
                "dataType": "DATE",
                "property": "createDate",
                "value": "2018-08-01"
              }
            ]
        }
      ],
      "operatorType": "AND"
    }
    ' 'http://localhost:8080/xxx'
    ```
    
1.  A || (B && C)  
   ```
   curl -v -X GET  -H "Cookie:JSESSIONID=9465BC28E76EDC99763F35067B594141" -H "Content-Type:application/json"  -d '
   {
     "criterionList": [
       {
         "compareType": "LIKE",
         "dataType": "STRING",
         "property": "name",
         "value": "name1"
       },
       {
           "operatorType": "AND",
           "criterionList": [
             {
               "compareType": "LESSEQUAL",
               "dataType": "DATE",
               "property": "createDate",
               "value": "2018-01-01"
             },
             {
               "compareType": "GREATEREQUAL",
               "dataType": "DATE",
               "property": "createDate",
               "value": "2018-08-01"
             }
           ]
       }
     ],
     "operatorType": "OR"
   }
   ' 'http://localhost:8080/xxx'
   ```
filter and sort sample just like:
```
curl -v -X GET  -H "Cookie:JSESSIONID=8C48F51917306907355F8FCCD3E26662" -H "Content-Type:application/json" -d '
 {
    "criterionList": [
        {
            "compareType": "LIKE",
            "dataType": "STRING",
            "property": "name",
            "value": "name1"
        }
    ],
    "operatorType": "AND",
    "sorts": [
        {
            "property": "createDate",
            "sortType": "DESC",
            "dataType": "DATE"
        },
        {
            "property": "id",
            "sortType": "DESC",
            "dataType": "LONG"
        }
    ]
}
    ' 'http://localhost:8080/xxx'
```

    

