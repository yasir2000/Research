(define (domain tropos-domain)

	(:requirements :adl :fluents :derived-predicates :durative-actions)

	(:types t_actor t_goal t_gtype)

	(:predicates
  
		(satisfied ?g - t_goal)
		(can_provide ?a - t_actor ?g - t_goal)
		(requests ?a2 - t_actor ?g - t_goal)
		(can_depend_on ?a1 ?a2 - t_actor)
		(and_subgoal2 ?g ?g1 ?g2 - t_goal)
		(and_subgoal3 ?g ?g1 ?g2 ?g3 - t_goal)
		(or_subgoal2 ?g ?g1 ?g2 - t_goal)
		(or_subgoal3 ?g ?g1 ?g2 ?g3 - t_goal)
		
		(order ?g_first ?g_end - t_goal) ; state that: goal g_firsrt must be satisfied before goal g_end. 

		; to be removed
		(is_means_end ?g1 ?g2 - t_goal)

		(busy ?a - t_actor)

		(subtype ?child ?parent - t_gtype)
		(type ?g - t_goal ?gt - t_gtype)
		(can_depend_on_gt ?a1 ?a2 - t_actor ?gt - t_gtype)
		(can_depend_on_g ?a1 ?a2 - t_actor ?g - t_goal)
		(can_decompose_gt ?a - t_actor ?gt - t_gtype)
		(can_provide_gt ?a - t_actor ?gt - t_gtype) 

		(pr_satisfies ?a - t_actor ?g - t_goal)
		(pr_and_decomposes2 ?a - t_actor ?g ?g1 ?g2 - t_goal)
		(pr_and_decomposes3 ?a - t_actor ?g ?g1 ?g2 ?g3 - t_goal)

		(pr_or_decomposes2 ?a - t_actor ?g ?g1 ?g2 - t_goal)
		(pr_or_decomposes3 ?a - t_actor ?g ?g1 ?g2 ?g3 - t_goal)
		(pr_passes ?a1 ?a2 - t_actor ?g - t_goal)
  
		(pr_passing ?a - t_actor ?g - t_goal)
		(pr_means_end ?g1 ?g2 - t_goal)
	)

;; functions

	(:functions
		; metric properties
		(satisfaction_coeff) ; contribution factor of total_satisfaction_degree in the final plan quanlity
		(work_load_coeff) ; contribution factor of total_work_load in the final plan quanlity
		(rt_total_satisfaction_degree) ; [RT] the total satisfaction degree of all goals.
		(rt_total_work_load) ; [RT]  the total work load of all actors to finish all goals (Overral plan cost).
		(rt_plan_quality)	; [RT] the final metric of a given solution, computed as
						;		plan_quality = satisfaction_coeff * total_satisfaction - work_load_coeff * total_work_load - [Solution-Length]*0.1
						;  

		; goal properties
		(min_satisfaction_degree ?g - t_goal) 
		(max_satisfaction_degree ?g - t_goal)

		;(load ?g - t_goal) ; workload for a goal
		(rt_satisfaction_degree ?g - t_goal)  ; [RT] specify current satisfaction level of all goals
		
		; actor properties
		(max_work_load ?a - t_actor)  ; maximum workload of an actor
		(max_job_at_hand ?a - t_actor) 
		(satisfaction_ability ?a - t_actor ?g - t_goal) 
		;100 = excellent, 75 = good, 50 = average, 25 = low, 0 = not provide

		(work_effort ?a - t_actor ?g - t_goal) 
		;100 = innocent ; 75 = has some knowledge; 50 = has good knowledge/trained; 25 = well-trained

		(time_effort ?a - t_actor ?g - t_goal)

		(rt_work_load ?a - t_actor)  ; [RT] current workload of an actor
		(rt_job_at_hand ?a - t_actor) ; [RT]
	
	)
;; derived predicates

	(:derived (can_depend_on_g ?r ?p - t_actor ?g - t_goal)
		(and
			(requests ?r ?g)
			(can_provide ?p ?g)
		)
	)

;; normal actions
	(:durative-action Infers
		:parameters (?g_means ?g_end - t_goal)
		:duration (= ?duration 1)
		:condition (and
			(at start (satisfied ?g_means))
			(at start (not (satisfied ?g_end)))
			(at start (is_means_end ?g_means ?g_end))
		)
		:effect (and
			(at end (satisfied ?g_end) )
			(at end (forall (?a - t_actor) (not (requests ?a ?g_end))) )
		)
	)

	(:durative-action Satisfies
        :parameters( ?a - t_actor ?g - t_goal )
		:duration (= ?duration (time_effort ?a ?g))
        :condition(and
				(over all (not (pr_satisfies ?a ?g)) )
				(over all (not (pr_passing ?a ?g)) )
				
				(over all (can_provide ?a ?g))
				
				(at start (requests ?a ?g ))

				(at start (<= (+ (rt_satisfaction_degree ?g) (satisfaction_ability ?a ?g)) (max_satisfaction_degree ?g)))
				(over all (<= (+ (rt_work_load ?a) (work_effort ?a ?g)) (max_work_load ?a)))
				(at start (not (busy ?a)))
				(over all (<= (rt_job_at_hand ?a) (max_job_at_hand ?a)))			
        )
        :effect( and

			(at start (busy ?a))

			(at end (not (requests ?a ?g)) )
			(at end (pr_satisfies ?a ?g) )
			(at end (satisfied ?g) )
			
			; actor effect
			(at end (increase (rt_satisfaction_degree ?g) (satisfaction_ability ?a ?g)) )
			(at end (increase (rt_work_load ?a) (work_effort ?a ?g)) )
			(at start (increase (rt_job_at_hand ?a) 1) )

			(at end (increase (rt_plan_quality) (/ (* (satisfaction_coeff) (satisfaction_ability ?a ?g) ) (* (work_load_coeff) (work_effort ?a ?g)))) )	

			(at end (not (busy ?a)))
		)
	)


	(:durative-action MeanEnds
		:parameters (?a - t_actor ?g1 ?g2 - t_goal)
		:duration(= ?duration 1)
		:condition( and
			(at start (requests ?a ?g1) )
			(at start (is_means_end ?g2 ?g1) )
			(at start (not (requests ?a ?g2)) )
			(at start (not (satisfied ?g2)) )
			(at start (not (pr_means_end ?g1 ?g2)) )
			
		)
		:effect ( and
			(at end (requests ?a ?g2) )
			(at end (pr_means_end ?g1 ?g2) )
			(at end (not (requests ?a ?g1)) )
			;(satisfied ?g1)
		)
	)
    

    (:durative-action AND_Decomposes2
        :parameters( ?a - t_actor ?g ?g1 ?g2 - t_goal )
		:duration(= ?duration 1)
        :condition( and
            (at start (and_subgoal2 ?g ?g1 ?g2 ) )
            (at start (requests ?a ?g) )
        )
        :effect( and
            (at end (requests ?a ?g1) )
            (at end (requests ?a ?g2) )
            (at end (not (requests ?a ?g) ) )
            (at end (pr_and_decomposes2 ?a ?g ?g1 ?g2) )
            )
    )

    (:durative-action AND_Decomposes3
        :parameters( ?a - t_actor ?g ?g1 ?g2 ?g3 - t_goal )
		:duration(= ?duration 1)
        :condition( and
            (at start (and_subgoal3 ?g ?g1 ?g2 ?g3 ) )
            (at start (requests ?a ?g) )
        )
        :effect( and
            (at end (requests ?a ?g1) )
            (at end (requests ?a ?g2) )
            (at end (requests ?a ?g3) )
            (at end (not (requests ?a ?g) ))
            (at end (pr_and_decomposes3 ?a ?g ?g1 ?g2 ?g3))
            )
    )
  
    (:durative-action OR_Decomposes2
        :parameters( ?a - t_actor ?g ?g1 ?g2 - t_goal )
		:duration(= ?duration 1)
        :condition( at start (and
            (or_subgoal2 ?g ?g1 ?g2 )
            (requests ?a ?g))
        )
        :effect( at end (and
            (requests ?a ?g1)
            (requests ?a ?g2)
            (not (requests ?a ?g) )
            (pr_or_decomposes2 ?a ?g ?g1 ?g2)
            ) )
    )

    (:durative-action OR_Decomposes3
        :parameters( ?a - t_actor ?g ?g1 ?g2 ?g3 - t_goal )
		:duration(= ?duration 1)
        :condition( at start (and
            (or_subgoal3 ?g ?g1 ?g2 ?g3 )
            (requests ?a ?g)
        ))
        :effect( at end (and
            (requests ?a ?g1)
            (requests ?a ?g2)
            (requests ?a ?g3)
            (not (requests ?a ?g) )
            (pr_or_decomposes3 ?a ?g ?g1 ?g2 ?g3)
            ))
    )

    (:durative-action Passes
        :parameters( ?a1 ?a2 - t_actor ?g - t_goal )
		:duration(= ?duration 1)
        :condition(at start (and
            (not (exists (?delegatee - t_actor) (pr_passes ?a2 ?delegatee ?g) ) )   
            (requests ?a1 ?g)
			(not (pr_passes ?a1 ?a2 ?g))
            (or (can_depend_on ?a1 ?a2)
                (can_depend_on_g ?a1 ?a2 ?g)
                ;(exists (?gt - t_gtype) (and (type ?g ?gt) (can_depend_on_gt ?a1 ?a2 ?gt) ) ) 
            )
         ))
        :effect(at end ( and
            (requests ?a2 ?g)
			(pr_passing ?a1 ?g)
			(pr_passes ?a1 ?a2 ?g)

			;; De co the delegate 1 goal dc nhieu lan, can phai giu nguyen (request ?a1 ?g).
			;; Tuy nhien, neu xoa dong "(not (requests ?a1 ?g))" thi se bao loi:
			;; Probolem: "The problem is unsolvable since at the fixpoint level the goals are mutex or not reachable"
			;; Trick: xoa va request lai
			(not (requests ?a1 ?g) ) ; rem dong nay de 1 goal co the delegate cho nhieu actor
			(requests ?a1 ?g)
            ))
    )

    (:durative-action CombinesAnd2  
         :parameters( ?g ?g1 ?g2 - t_goal )
		 :duration(= ?duration 1)
         :condition(at start ( and
			;(not (satisfied ?g))
            (and_subgoal2 ?g ?g1 ?g2 )
            (satisfied ?g1)
            (satisfied ?g2)
         ))
         :effect ( at end (satisfied ?g) )
    )

    (:durative-action CombinesAnd3  
         :parameters( ?g ?g1 ?g2 ?g3 - t_goal )
		 :duration(= ?duration 1)
         :condition( at start (and
			;(not (satisfied ?g))
            (and_subgoal3 ?g ?g1 ?g2 ?g3 )
            (satisfied ?g1)
            (satisfied ?g2)
            (satisfied ?g3)
         ))
         :effect(at end (satisfied ?g ))
    )

    (:durative-action CombinesOr2
         :parameters( ?g ?g1 ?g2 - t_goal )
		 :duration(= ?duration 1)
         :condition( at start (and
			;(not (satisfied ?g))
            (or_subgoal2 ?g ?g1 ?g2 )
            (or (satisfied ?g1)  (satisfied ?g2))
         ))
         :effect ( at end (satisfied ?g ))
    )

    (:durative-action CombinesOr3
		 :parameters( ?g ?g1 ?g2 ?g3 - t_goal )
		 :duration(= ?duration 1)
         :condition( at start (and
			;(not (satisfied ?g))
            (or_subgoal3 ?g ?g1 ?g2 ?g3)
            (or (satisfied ?g1)  (satisfied ?g2) (satisfied ?g3))
         ))
         :effect (at end (satisfied ?g ))
    )

)