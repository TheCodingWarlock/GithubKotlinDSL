/*
 *  Copyright (C) 2019 Eton Otieno
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.devbits.githubkotlindsl.simple

import io.devbits.githubkotlindsl.models.Contributor
import io.devbits.githubkotlindsl.models.Repo
import io.devbits.githubkotlindsl.models.RepoOwner

class RepoBuilder {
    var repoName: String = ""
    var description: String = ""
    var stars: Int = 0
    private lateinit var owner: RepoOwner
    private val contributors = mutableListOf<Contributor>()

    fun owner(block: RepoOwnerBuilder.() -> Unit) {
        owner = RepoOwnerBuilder().apply(block).build()
    }

    fun contributor(builder: ContributorBuilder.() -> Unit) {
        val contributor = ContributorBuilder().apply(builder).build()
        this.contributors.plusAssign(contributor)
    }

    fun build(): Repo = Repo(repoName, description, owner, stars, contributors)
}

class RepoOwnerBuilder {
    var name = ""
    var url = ""

    fun build(): RepoOwner = RepoOwner(name, url)
}

class ContributorBuilder {

    var contributorName = ""
    var contributions = 0

    fun build(): Contributor = Contributor(contributorName, contributions)
}

fun repo(builder: RepoBuilder.() -> Unit): Repo = RepoBuilder().apply(builder).build()