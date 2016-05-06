#!/bin/bash
//usr/bin/env groovy -classpath ${project.artifactId}-${project.version}.jar -d "$0" $@; exit $?

//-----------------------------------------------------------------------------
/*
 * This file is part of DeployTools.
 * 
 * DeployTools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DeployTools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DeployTools. If not, see <http://www.gnu.org/licenses/>.
 */

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.cli.SourceDeps;

//-----------------------------------------------------------------------------
SourceDeps.main( args )
