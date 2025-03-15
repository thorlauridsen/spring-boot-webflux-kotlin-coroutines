package com.github.thorlauridsen.controller

import com.github.thorlauridsen.dto.CustomerDto
import com.github.thorlauridsen.dto.CustomerInputDto
import com.github.thorlauridsen.dto.toDto
import com.github.thorlauridsen.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import java.net.URI
import java.util.UUID

/**
 * REST controller for customers.
 * This controller consists of endpoints for:
 * - Saving customers.
 * - Fetching customers.
 *
 * @param customerService [CustomerService] service layer.
 */
@Controller
class CustomerController(private val customerService: CustomerService) : ICustomerController {

    /**
     * Retrieve a customer given an id.
     * Get the customer from the service and convert it to a DTO before returning it.
     * @param id [UUID] of customer.
     * @return [CustomerDto]
     */
    override fun getCustomer(id: UUID): ResponseEntity<CustomerDto> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity.ok(customer.toDto())
    }

    /**
     * Save a customer.
     * Convert the customer to a model before saving it.
     * Create the location for the newly created customer.
     * Return location and customer DTO.
     * @param customer [CustomerInputDto] to save.
     * @return Saved [CustomerDto]
     */
    override fun saveCustomer(customer: CustomerInputDto): ResponseEntity<CustomerDto> {
        val savedCustomer = customerService.saveCustomer(customer.toModel())

        val location = URI.create("$CUSTOMER_BASE_ENDPOINT/${savedCustomer.id}")
        return ResponseEntity.created(location).body(savedCustomer.toDto())
    }
}
