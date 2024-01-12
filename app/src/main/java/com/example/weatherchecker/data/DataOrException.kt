package com.example.weatherchecker.data

class DataOrException < T, B, E> (
    var data : T ?= null,
    var loading: B ?= null,

    var exception : E ?= null


)
