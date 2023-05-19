package com.example.herohub.domain

/**
 * Created by Asia sama on 5/19/2023.
 * sehunexo710@gmail.com
 */
interface Mapper<I, O> {
    fun map(input: I): O
}