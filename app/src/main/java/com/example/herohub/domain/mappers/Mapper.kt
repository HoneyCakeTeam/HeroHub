package com.example.herohub.domain.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}