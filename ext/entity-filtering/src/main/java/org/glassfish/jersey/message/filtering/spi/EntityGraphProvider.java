/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2015 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.jersey.message.filtering.spi;

import java.util.Set;

import org.glassfish.jersey.spi.Contract;

/**
 * Provides {@link EntityGraph entity graph} and {@link ObjectGraph object graph} instances.
 *
 * @author Michal Gajdos
 */
@Contract
public interface EntityGraphProvider {

    /**
     * Get an entity graph for given class. New graph is created if no graph exists for given class.
     *
     * @param entityClass entity class the graph should be created for.
     * @param forWriter flag determining whether the graph should be created for writer/reader.
     * @return an entity graph.
     */
    public EntityGraph getOrCreateEntityGraph(final Class<?> entityClass, final boolean forWriter);

    /**
     * Get an empty entity graph for given class. New graph is created if the stored one is not an empty entity graph or no graph
     * exists for given class. This method overrides the graph created by {@link #getOrCreateEntityGraph(Class, boolean)} method.
     *
     * @param entityClass entity class the graph should be created for.
     * @param forWriter flag determining whether the graph should be created for writer/reader.
     * @return an empty entity graph.
     */
    public EntityGraph getOrCreateEmptyEntityGraph(final Class<?> entityClass, final boolean forWriter);

    /**
     * Determine whether an entity graph for given entity class has been created by this provider.
     *
     * @param entityClass entity class for which the graph should be checked.
     * @param forWriter flag determining whether the check should be in writer/reader graphs.
     * @return {@code true} if the entity graph already exists, {@code false} otherwise.
     */
    public boolean containsEntityGraph(final Class<?> entityClass, final boolean forWriter);

    /**
     * Create an {@code ObjectGraph} for given parameters. Every time this method is called a new instance of object graph is
     * created.
     *
     * @param entityClass entity class which the object graph should be created for.
     * @param filteringScopes entity-filtering scopes the graph should be created for.
     * @param forWriter flag determining whether the graph should be created for writer/reader.
     * @return an entity-filtering object graph instance.
     */
    public ObjectGraph createObjectGraph(final Class<?> entityClass, final Set<String> filteringScopes, final boolean forWriter);
}
