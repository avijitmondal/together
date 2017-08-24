package com.avijit.together.server.service;

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
		if (page.hasPrevious()) {
			Link prevLink = buildPageLink(pageParam, page.getNumber() - 1, sizeParam, page.getSize(),
					Link.REL_PREVIOUS);
			add(prevLink);
		}
	}

	private void addSelfLink(String pageParam, String sizeParam) {
		Link selfLink = buildPageLink(pageParam, page.getNumber(), sizeParam, page.getSize(), Link.REL_SELF);
		add(selfLink);
	}

	private void addNextLink(String pageParam, String sizeParam) {
		if (page.hasNext()) {
			Link nextLink = buildPageLink(pageParam, page.getNumber() + 1, sizeParam, page.getSize(), Link.REL_NEXT);
			add(nextLink);
		}
	}

	private void addLastLink(String pageParam, String sizeParam) {
		Link prevLink = buildPageLink(pageParam, page.getTotalPages() - 1, sizeParam, page.getSize(), Link.REL_LAST);
		add(prevLink);
	}

	private void addFirstLink(String pageParam, String sizeParam) {
		Link prevLink = buildPageLink(pageParam, 0, sizeParam, page.getSize(), Link.REL_FIRST);
		add(prevLink);
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
		// TODO Auto-generated method stub
		return page.getNumberOfElements();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return page.getSize();
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return page.getSort();
	}

	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		return page.hasContent();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return page.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return page.hasPrevious();
	}

	@Override
	public boolean isFirst() {
		// TODO Auto-generated method stub
		return page.isFirst();
	}

	@Override
	public boolean isLast() {
		// TODO Auto-generated method stub
		return page.isLast();
	}

	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return page.nextPageable();
	}

	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return page.previousPageable();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return page.iterator();
	}

	@Override
	public long getTotalElements() {
		// TODO Auto-generated method stub
		return page.getTotalElements();
	}

	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		return page.getTotalPages();
	}

	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
