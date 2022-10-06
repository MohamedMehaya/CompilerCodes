 s(s(S)) --> sentence(S).
 s(s(S,CO,S)) --> sentence(S),conjunction(CO),sentence(S).
 
 sentence(s(NP,VP)) --> noun_phrase(NP), verb_phrase(VP).
 sentence(s(V,CO,V)) --> noun_phrase(NP),conjunction(CO),verb_phrase(VP).
 sentence(s(V,CO,V)) --> verb(V),conjunction(CO),verb(V).
 
 
 
 
 
 noun_phrase(np(N)) --> noun(N).
 noun_phrase(np(D,N)) --> det(D), noun(N).
 noun_phrase(np(ADJ,NP)) --> adjective(ADJ),noun_phrase(NP).
 noun_phrase(np(NP,ADJ,NP)) --> noun_phrase(NP),adjective(ADJ),noun_phrase(NP).
 noun_phrase(np(NP,PR,NP)) --> noun_phrase(NP),preposition(PR),noun_phrase(NP).
 noun_phrase(np(D,ADJ,N)) --> det(D), adjective(ADJ), noun(N).
 noun_phrase(np(D,ADJ,N,PR,V)) --> det(D), adjective(ADJ), noun(N), pron(PR),verb(V).
 
 verb_phrase(vp(V)) --> verb(V).
 verb_phrase(vp(V,NP)) --> verb(V), noun_phrase(NP).
 verb_phrase(vp(V,CO,VP)) --> verb(V),conjunction(CO),verb_phrase(VP).
 verb_phrase(vp(V,PR,VP)) --> verb(V),preposition(PR),verb_phrase(VP).
 verb_phrase(vp(V,PR,NP,PR,NP)) --> verb(V),preposition(PR),noun_phrase(NP),preposition(PR),noun_phrase(NP).
 
 det(d(The)) --> [The].
 det(d(the)) --> [the].
 det(d(a)) --> [a].
 det(d(an)) --> [an].
 det(d(some)) --> [some].
 det(d(every)) --> [every].
 det(d(his)) --> [his].
 det(d(her)) --> [her].
 det(d(their)) --> [their].
 
 
 conjunction(conj(and)) --> [and].
 conjunction(conj(but)) --> [but].
 conjunction(conj(or)) --> [or].
 
 %Nouns
 
 noun(n(bat)) --> [bat].
 noun(n(cat)) --> [cat].
 noun(n(sock)) --> [sock].
 noun(n(hero)) --> [hero].
 noun(n(cat)) --> [ship].
 noun(n(monkey)) --> [monkey].
 noun(n(baby)) --> [baby].
 noun(n(match)) --> [match].
 noun(n(boy)) --> [boy].
 noun(n(year)) --> [year].
 noun(n(people)) --> [people].
 noun(n(way)) --> [way].
 noun(n(day)) --> [day].
 noun(n(man)) --> [man].
 noun(n(thing)) --> [thing].
 noun(n(woman)) --> [woman].
 noun(n(life)) --> [life].
 noun(n(child)) --> [child].
 noun(n(world)) --> [world].
 noun(n(school)) --> [school].
 noun(n(person)) --> [person].
 noun(n(man)) --> [man].
 noun(n(box)) --> [box].
 
 
 
 
 
 
 
 %Adverbs

 adverb(ad(angrily)) --> [angrily].
 adverb(ad(anxiously)) --> [anxiously].
 adverb(ad(boldly)) --> [boldly].
 adverb(ad(cheerfully)) --> [cheerfully].
 adverb(ad(finally)) --> [finally].
 adverb(ad(mysteriously)) --> [mysteriously].
 adverb(ad(poorly)) --> [poorly].
 adverb(ad(quickly)) --> [quickly].
 adverb(ad(warmly)) --> [warmly].
 adverb(ad(mortally)) --> [mortally].
 
 
 %Verbs
 
 verb(v(ate)) --> [ate].
 verb(v(pushed)) --> [pushed].
 verb(v(stored)) --> [stored].
 verb(v(typed)) --> [typed].
 verb(v(worked)) --> [worked].
 verb(v(wrote)) --> [wrote].
 verb(v(smiled)) --> [smiled].
 verb(v(wore)) --> [wore].
 verb(v(read)) --> [read].
 verb(v(ran)) --> [ran].
 verb(v(added)) --> [added].
 verb(v(admired)) --> [admired].
 verb(v(admitted)) --> [admitted].
 verb(v(advised)) --> [advised].
 verb(v(agreed)) --> [agreed].
 verb(v(announced)) --> [announced].
 verb(v(baked)) --> [baked].
 verb(v(behaved)) --> [behaved].
 verb(v(blined)) --> [blined].
 verb(v(worked)) --> [worked].
 verb(v(blushed)) --> [blushed].
 verb(v(camped)) --> [camped].
 verb(v(collected)) --> [collected].
 verb(v(crashed)) --> [crashed].
 verb(v(deserved)) --> [deserved].
 
 
 %Adjectives
 
 adjective(adj(blue)) --> [blue].
 adjective(adj(young)) --> [young].
 adjective(adj(old)) --> [old].
 adjective(adj(yellow)) --> [yellow].
 adjective(adj(rare)) --> [rare].
 adjective(adj(bright)) --> [bright].
 adjective(adj(white)) --> [white].
 adjective(adj(black)) --> [black].
 adjective(adj(golden)) --> [golden].
 adjective(adj(black)) --> [black].
 adjective(adj(Adamant)) --> [Adamant].
 adjective(adj(Adroit)) --> [Adroit].
 adjective(adj(Antic)) --> [Antic].
 adjective(adj(Arcadian)) --> [Arcadian].
 adjective(adj(Corpulent)) --> [Corpulent].
 adjective(adj(Dilatory)) --> [Dilatory].
 adjective(adj(Efficacious)) --> [Efficacious].
 adjective(adj(Effulgent)) --> [Effulgent].
 adjective(adj(Friable)) --> [Friable].
 adjective(adj(Intransigent)) --> [Intransigent].
 adjective(adj(big)) --> [big].
 adjective(adj(empty)) --> [empty].
 

 pronoun(pron(who)) -->[who].
 
 
 
 preposition(prep(for))  -->[for].
 preposition(prep(on))  -->[on].
 preposition(prep(in))  -->[in].
 preposition(prep(at))  -->[at].
 preposition(prep(before))  -->[before].
 

