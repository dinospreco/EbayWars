package ebaywars.searchengines;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Olx {
	
	private String olxSearchLink;
	private String olxPageNumberSelect;
	
	public Olx() 
	{
		olxSearchLink = "https://www.olx.ba/pretraga?trazilica=";
	    olxPageNumberSelect = "&stranica=";
	}
	
	public String getSearchLink()
	{
		return olxSearchLink;
	}
	
	public String getPageNumberSelect()
	{
		return olxPageNumberSelect;
	}
	
	public Set<String> getArticleLinks(String keyWord)
	{
		Document olxPageScraper;
		Elements olxLinks;
		Set<String> articleLinks = new HashSet<String>();
		int pageNumber = 1;
		
		try
		{
			while (true) {
//				System.out.println("Stranica broj " + pageNumber);
				
				if (pageNumber == 1) 
				{
					olxPageScraper = Jsoup.connect(getSearchLink() + keyWord).userAgent("Mozilla/17.0").get();
				} 
				else 
				{
					olxPageScraper = Jsoup.connect(getSearchLink() + keyWord + getPageNumberSelect() + pageNumber)
							.userAgent("Mozilla/17.0").get();
				}
				olxLinks = olxPageScraper.select("div.naslov").select("a");
				
				for (Element link : olxLinks) 
				{
					articleLinks.add(link.attr("href"));
				} 
				if (olxLinks.size()!=30)
				{
					break;
				}
				else
				{
					pageNumber++;
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return articleLinks;
	}
	
	public Set<String> getArticleLinks(String keyWord, int totalPagesNumber)
	{
		Document olxPageScraper;
		Elements olxLinks;
		Set<String> articleLinks = new HashSet<String>();
		int pageNumber = 1;
		
		try
		{
			while (true) {
//				System.out.println("Stranica broj " + pageNumber);
				
				if (pageNumber == 1) 
				{
					olxPageScraper = Jsoup.connect(getSearchLink() + keyWord).userAgent("Mozilla/17.0").get();
				} 
				else 
				{
					olxPageScraper = Jsoup.connect(getSearchLink() + keyWord + getPageNumberSelect() + pageNumber)
							.userAgent("Mozilla/17.0").get();
				}
				olxLinks = olxPageScraper.select("div.naslov").select("a");
				
				for (Element link : olxLinks) 
				{
					articleLinks.add(link.attr("href"));
				} 
				if (olxLinks.size()!=30 || pageNumber == totalPagesNumber)
				{
					break;
				}
				else
				{
					pageNumber++;
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return articleLinks;
	}
}
