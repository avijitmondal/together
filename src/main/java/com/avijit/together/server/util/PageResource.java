package com.avijit.together.server.util;

import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class PageResource<T> extends ResourceSupport implements Page<T> {

	private final Page<T> page;

	public PageResource(Page<T> page, String pageParam, String sizeParam) {
		super();
		this.page = page;
		addPreviousLink(pageParam, sizeParam);
		addNextLink(pageParam, sizeParam);
		addFirstLink(pageParam, sizeParam);
		addLastLink(pageParam, sizeParam);
		addSelfLink(pageParam, sizeParam);
	}

	private void addPreviousLink(String pageParam, String sizeParam) {
		if (null != page && page.hasPrevious()) {
			Link prevLink = buildPageLink(pageParam, page.getNumber() - 1, sizeParam, page.getSize(),
					Link.REL_PREVIOUS);
			add(prevLink);
		}
	}

	private void addSelfLink(String pageParam, String sizeParam) {
		if (null != page) {
			Link selfLink = buildPageLink(pageParam, page.getNumber(), sizeParam, page.getSize(), Link.REL_SELF);
			add(selfLink);
		}
	}

	private void addNextLink(String pageParam, String sizeParam) {
		if (null != page && page.hasNext()) {
			Link nextLink = buildPageLink(pageParam, page.getNumber() + 1, sizeParam, page.getSize(), Link.REL_NEXT);
			add(nextLink);
		}
	}

	private void addLastLink(String pageParam, String sizeParam) {
		if (null != page && !page.isLast()) {
			Link prevLink = buildPageLink(pageParam, page.getTotalPages() - 1, sizeParam, page.getSize(),
					Link.REL_LAST);
			add(prevLink);
		}
	}

	private void addFirstLink(String pageParam, String sizeParam) {
		if (null != page && !page.isFirst()) {
			Link prevLink = buildPageLink(pageParam, 0, sizeParam, page.getSize(), Link.REL_FIRST);
			add(prevLink);
		}
	}

	private ServletUriComponentsBuilder createBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}

	private Link buildPageLink(String pageParam, int page, String sizeParam, int size, String rel) {
		String path = createBuilder().queryParam(pageParam, page).queryParam(sizeParam, size).build().toUriString();
		Link link = new Link(path, rel);
		return link;
	}

	@Override
	public List<T> getContent() {
		return page.getContent();
	}

	@Override
	public int getNumber() {
		return page.getNumber();
	}

	@Override
	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	@Override
	public int getSize() {
		return page.getSize();
	}

	@Override
	public Sort getSort() {
		return page.getSort();
	}

	@Override
	public boolean hasContent() {
		return page.hasContent();
	}

	@Override
	public boolean hasNext() {
		return page.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	@Override
	public boolean isFirst() {
		return page.isFirst();
	}

	@Override
	public boolean isLast() {
		return page.isLast();
	}

	@Override
	public Pageable nextPageable() {
		return page.nextPageable();
	}

	@Override
	public Pageable previousPageable() {
		return page.previousPageable();
	}

	@Override
	public Iterator<T> iterator() {
		return page.iterator();
	}

	@Override
	public long getTotalElements() {
		return page.getTotalElements();
	}

	@Override
	public int getTotalPages() {
		return page.getTotalPages();
	}

	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> arg0) {
		return null;
	}

}
