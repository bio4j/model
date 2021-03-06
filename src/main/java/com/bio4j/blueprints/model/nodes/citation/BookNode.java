
package com.bio4j.blueprints.model.nodes.citation;

import com.bio4j.blueprints.model.Vertex;
import com.bio4j.blueprints.model.nodes.CityNode;
import com.bio4j.blueprints.model.nodes.PersonNode;
import com.bio4j.blueprints.model.nodes.ProteinNode;
import com.bio4j.blueprints.model.relationships.citation.book.*;
import com.bio4j.model.nodes.City;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.Book;
import com.bio4j.model.nodes.citation.Publisher;
import com.tinkerpop.blueprints.Direction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The reference information for articles found in books or other types of publication includes
 * the book name, the volume number, the page range, the publisher, the city and the year.
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public final class BookNode extends Vertex implements Book{

    public static final String NODE_TYPE = BookNode.class.getCanonicalName();

    /** Book name **/
    public static final String NAME_PROPERTY = "book_name";
    /** Year **/
    public static final String DATE_PROPERTY = "book_date";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "book";


    public BookNode(com.tinkerpop.blueprints.Vertex v){
        super(v, NODE_TYPE);
    }


    @Override
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}
    @Override
    public String getDate(){    return String.valueOf(vertex.getProperty(DATE_PROPERTY));}


    @Override
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}
    @Override
    public void setDate(String value){  vertex.setProperty(DATE_PROPERTY, value);}
    
    
    @Override
    public List<Protein> getProteinCitations(){
        List<Protein> list = new LinkedList<Protein>();
        Iterator<com.tinkerpop.blueprints.Vertex> iterator = vertex.getVertices(Direction.OUT, BookProteinCitationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }
    
    /**
     * Gets the Book publisher
     * @return 
     */
    @Override
    public Publisher getPublisher(){
        Iterator<com.tinkerpop.blueprints.Vertex> iterator = vertex.getVertices(Direction.OUT, BookPublisherRel.NAME).iterator();
        if(iterator.hasNext()){
            return new PublisherNode(iterator.next());
        }else{
            return null;
        }        
    }
    
    /**
     * Gets the city where the book was published
     * @return 
     */
    @Override
    public City getCity(){
        Iterator<com.tinkerpop.blueprints.Vertex> iterator = vertex.getVertices(Direction.OUT, BookCityRel.NAME).iterator();
        if(iterator.hasNext()){
            return new CityNode(iterator.next());
        }else{
            return null;
        }        
    }
    
    /**
     * gets the authors of the book
     * @return 
     */
    @Override
    public List<Person> getAuthors(){
        List<Person> list = new LinkedList<Person>();
        Iterator<com.tinkerpop.blueprints.Vertex> iterator = vertex.getVertices(Direction.OUT, BookAuthorRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next()));
        }
        return list;
    }
    /**
     * gets the editors of the book
     * @return 
     */
    @Override
    public List<Person> getEditors(){
        List<Person> list = new LinkedList<Person>();
        Iterator<com.tinkerpop.blueprints.Vertex> iterator = vertex.getVertices(Direction.OUT, BookEditorRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next()));
        }
        return list;
    }

    @Override
    public String toString(){
        return "name = " + getName() + "\n" +
                "date = " + getDate();
    }

}