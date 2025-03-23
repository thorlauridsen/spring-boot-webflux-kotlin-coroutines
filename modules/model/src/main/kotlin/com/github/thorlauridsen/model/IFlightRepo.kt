package com.github.thorlauridsen.model

/**
 * Flight repository interface.
 * This is an interface containing methods for interacting with the flight table.
 * A repository class will implement this interface to provide the actual implementation.
 * This interface makes it easier to swap out the implementation of the repository if needed.
 */
interface IFlightRepo {

    /**
     * Save a flight to the database.
     * @param flight [Flight] to save.
     * @return [Flight] retrieved from database.
     */
    fun save(flight: Flight): Flight

    /**
     * Get all flights from the database.
     * @return List of [Flight]
     */
    fun findAll(): List<Flight>
}
