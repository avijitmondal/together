/*****************************************************************************
 * FILE NAME   : IFileNameRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 18, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.database.repository;

import com.avijitmondal.together.database.dao.FileName;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author avijit
 *
 */
@Repository("iFileNameRepository")
public interface IFileNameRepository extends PagingAndSortingRepository<FileName, String> {

}
