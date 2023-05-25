package com.example.herohub.data.remote

import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.remote.model.CharacterDto
import com.example.herohub.data.remote.model.ComicDto
import com.example.herohub.data.remote.model.EventDto
import com.example.herohub.data.remote.model.SeriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelService {

    @GET("characters")
    suspend fun getCharactersByName(
        @Query("nameStartsWith")
        name: String,
    ): Response<BaseResponse<CharacterDto>>

    @GET("events/{eventId}")
    suspend fun getEvent(
        @Path("eventId") eventId: Int,
    ): Response<BaseResponse<EventDto>>

    @GET("characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: Int,
    ): Response<BaseResponse<CharacterDto>>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Int,
    ): Response<BaseResponse<CharacterDto>>

    @GET("characters/{characterId}/events")
    suspend fun getCharacterEvents(
        @Path("characterId") characterId: Int,
    ): Response<BaseResponse<EventDto>>

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
    ): Response<BaseResponse<ComicDto>>

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: Int,
    ): Response<BaseResponse<SeriesDto>>


    @GET("comics/{comicId}")
    suspend fun getComic(
        @Path("comicId") comicId: Int,
    ): Response<BaseResponse<ComicDto>>

    @GET("comics")
    suspend fun getAllComics(
        @Query("limit") limit: Int,
    ): Response<BaseResponse<ComicDto>>

    @GET("events")
    suspend fun getAllEvents(
        @Query("limit") limit: Int,
    ): Response<BaseResponse<EventDto>>

    @GET("series/{seriesId}")
    suspend fun getSeriesDetails(
        @Path("seriesId") seriesId: Int,
    ): Response<BaseResponse<SeriesDto>>

    @GET("series")
    suspend fun getAllSeries(
        @Query("limit") limit: Int,
    ): Response<BaseResponse<SeriesDto>>

}

//override suspend fun refreshComics() {
//    wrap({ api.getAllComics(100) }, dtoToEntityContainer.comicMapper::map).apply {
//        toData()?.let { comicEntity ->
//            dao.insertAllComics(comicEntity)
//        }
//    }
//}
//override suspend fun getAllComics(): UiState<List<Comic>> {
//    return wrap({ api.getAllComics(100) },
//        { dtoToDomainContainer.comicDtoToComic.map(it) })
//}


//private suspend fun <I, O> wraState(
//    response: suspend () -> Response<BaseResponse<I>>,
//    map: (List<I>) -> O,
//): Flow<UiState<O>> {
//    return flow {
//        try {
//            if (response().isSuccessful) {
//                emit(UiState.Success(map(response().body()?.data?.results ?: emptyList())))
//            } else {
//                emit(UiState.Error(response().message()))
//            }
//        } catch (e: Exception) {
//            emit(UiState.Error(e.message ?: "Unknown error occurred"))
//        }
//    }
//}


