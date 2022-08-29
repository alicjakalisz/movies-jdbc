`POST api/v1/movies `

Request:
```aidl
{
    "name" : "The avengers",
    "releaseDate" : "2022-09-20",
}
```

Response:
```aidl
200
{
    "id" : 1
    "name" : "The avengers",
    "releaseDate" : "2022-09-20",
    "actors" :[]
}
```

It creates a new movie, and add actors to the movie. In case that the actor id field is empty it should create a new actor.

`GET api/v1/movies/{id}`

Response:

```aidl
200
{
    "id" : 1,
    "name"  : "Avengers",
    "releaseDate" :  "2020-02-01",
    "actors" : [
        {
            "id" : 1,
            "name" : "Brad Pitt"
        },
        {
            "id" : 2,
            "name" : "Angelina Jolie"
        }
    ]
}
```

`GET api/v1/movies`

Response:

```aidl
200
{   
    [
        {
            "id" : 1,
            "name"  : "Avengers",
            "releaseDate" :  "2020-02-01",
            "actors" : [
                {
                    "id" : 1,
                    "name" : "Brad Pitt"
                },
                {
                    "id" : 2,
                    "name" : "Angelina Jolie"
                }
            ]
        },
        {
            "id" : 2,
            "name"  : "Avengers 2",
            "releaseDate" :  "2020-02-01",
            "actors" : [
                {
                    "id" : 1,
                    "name" : "Brad Pitt"
                },
                {
                    "id" : 2,
                    "name" : "Angelina Jolie"
                }
            ]
        }            
    ]
}
```


`DELETE api/v1/movies/{id}`

Response:

```aidl
200 {}
```

`GET api/v1/movies/search?actorId=${actorId}&actorName=${actorName}&movieName=${movieName}`

Response:

```aidl
200
{   
    [
        {
            "id" : 1,
            "name"  : "Avengers",
            "releaseDate" :  "2020-02-01",
            "actors" : [
                {
                    "id" : 1,
                    "name" : "Brad Pitt"
                },
                {
                    "id" : 2,
                    "name" : "Angelina Jolie"
                }
            ]
        },
        {
            "id" : 2,
            "name"  : "Avengers 2",
            "releaseDate" :  "2020-02-01",
            "actors" : [
                {
                    "id" : 1,
                    "name" : "Brad Pitt"
                },
                {
                    "id" : 2,
                    "name" : "Angelina Jolie"
                }
            ]
        }            
    ]
}
```


`POST api/v1/movies/${movieId}/${actorId}`

Response:

```
200
{
    "id" : 1,
    "name"  : "Avengers",
    "releaseDate" :  "2020-02-01",
    "actors" : [
        {
            "id" : 1,
            "name" : "Brad Pitt"
        },
        {
            "id" : 2,
            "name" : "Angelina Jolie"
        }
    ]
}

```