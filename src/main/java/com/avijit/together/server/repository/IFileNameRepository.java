/*****************************************************************************
 * FILE NAME   : IFileNameRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 18, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.FileName;

/**
 * @author avijit
 *
 */
@Repository("iFileNameRepository")
public interface IFileNameRepository extends PagingAndSortingRepository<FileName, String> {

}
