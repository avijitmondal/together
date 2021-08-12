package com.avijitmondal.together.core.util.ws;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author avijit
 *
 * @param <T>
 *            Return type of pageable
 */
public class PageResource<T> extends RepresentationModel implements Page<T> {

	/**
	 * Contains a page of pageable
	 */
	private final Page<T> page;

	/**
	 * @param page
	 *            contains a page of pageable
	 * @param pageParam
	 *            intended page number
	 * @param sizeParam
	 *            intended page size
	 */
	public PageResource(Page<T> page, String pageParam, String sizeParam) {
		super();
		this.page = page;
		addPreviousLink(pageParam, sizeParam);
		addNextLink(pageParam, sizeParam);
		addFirstLink(pageParam, sizeParam);
		addLastLink(pageParam, sizeParam);
		addSelfLink(pageParam, sizeParam);
	}

	/**
	 * @param pageParam
	 * @param sizeParam
	 */
	private void addPreviousLink(String pageParam, String sizeParam) {
		if (null != page && page.hasPrevious()) {
			Link prevLink = buildPageLink(pageParam, page.getNumber() - 1, sizeParam, page.getSize(),
					IanaLinkRelations.PREV.value());
			add(prevLink);
		}
	}

	/**
	 * @param pageParam
	 * @param sizeParam
	 */
	private void addSelfLink(String pageParam, String sizeParam) {
		if (null != page) {
			Link selfLink = buildPageLink(pageParam, page.getNumber(), sizeParam, page.getSize(), IanaLinkRelations.SELF.value());
			add(selfLink);
		}
	}

	/**
	 * @param pageParam
	 * @param sizeParam
	 */
	private void addNextLink(String pageParam, String sizeParam) {
		if (null != page && page.hasNext()) {
			Link nextLink = buildPageLink(pageParam, page.getNumber() + 1, sizeParam, page.getSize(), IanaLinkRelations.NEXT.value());
			add(nextLink);
		}
	}

	/**
	 * @param pageParam
	 * @param sizeParam
	 */
	private void addLastLink(String pageParam, String sizeParam) {
		if (null != page && !page.isLast()) {
			Link prevLink = buildPageLink(pageParam, page.getTotalPages() - 1, sizeParam, page.getSize(),
					IanaLinkRelations.LAST.value());
			add(prevLink);
		}
	}

	/**
	 * @param pageParam
	 * @param sizeParam
	 */
	private void addFirstLink(String pageParam, String sizeParam) {
		if (null != page && !page.isFirst()) {
			Link prevLink = buildPageLink(pageParam, 0, sizeParam, page.getSize(), IanaLinkRelations.FIRST.value());
			add(prevLink);
		}
	}

	/**
	 * @return
	 */
	private ServletUriComponentsBuilder createBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}

	private Link buildPageLink(String pageParam, int page, String sizeParam, int size, String rel) {
		String path = createBuilder().queryParam(pageParam, page).queryParam(sizeParam, size).build().toUriString();
		return new Link(path, rel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getContent()
	 */
	@Override
	public List<T> getContent() {
		return page.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getNumber()
	 */
	@Override
	public int getNumber() {
		return page.getNumber();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getNumberOfElements()
	 */
	@Override
	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getSize()
	 */
	@Override
	public int getSize() {
		return page.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getSort()
	 */
	@Override
	public Sort getSort() {
		return page.getSort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#hasContent()
	 */
	@Override
	public boolean hasContent() {
		return page.hasContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return page.hasNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#hasPrevious()
	 */
	@Override
	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#isFirst()
	 */
	@Override
	public boolean isFirst() {
		return page.isFirst();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#isLast()
	 */
	@Override
	public boolean isLast() {
		return page.isLast();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#nextPageable()
	 */
	@Override
	public Pageable nextPageable() {
		return page.nextPageable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#previousPageable()
	 */
	@Override
	public Pageable previousPageable() {
		return page.previousPageable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return page.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getTotalElements()
	 */
	@Override
	public long getTotalElements() {
		return page.getTotalElements();
	}

	@Override
	public <U> Page<U> map(Function<? super T, ? extends U> function) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getTotalPages()
	 */
	@Override
	public int getTotalPages() {
		return page.getTotalPages();
	}

}
