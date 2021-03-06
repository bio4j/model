/*
 * Copyright (C) 2010-2013  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.bio4j.blueprints.model.relationships.protein;

import com.bio4j.blueprints.model.nodes.EnzymeNode;
import com.bio4j.blueprints.model.nodes.ProteinNode;
import com.bio4j.model.relationships.protein.ProteinEnzymaticActivity;
import com.tinkerpop.blueprints.Direction;
import com.bio4j.blueprints.model.Edge;

/**
 * Protein enzymatic activity (EC number connections)
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public final class ProteinEnzymaticActivityRel extends Edge implements ProteinEnzymaticActivity{

    public static final String NAME = "PROTEIN_ENZYMATIC_ACTIVITY";

    public ProteinEnzymaticActivityRel(com.tinkerpop.blueprints.Edge e){
        super(e);
    }
    
    @Override
    public ProteinNode getProtein(){
        return new ProteinNode(getVertex(Direction.IN));
    }
    
    @Override
    public EnzymeNode getEnzyme(){
        return new EnzymeNode(getVertex(Direction.OUT));
    }

    @Override
    public String getType() {
        return NAME;
    }

}