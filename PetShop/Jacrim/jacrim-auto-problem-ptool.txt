(define (problem ptool)
	(:domain tropos-domain)
	(:objects
		A2 - t_actor
		A1 - t_actor
		A4 - t_actor
		A3 - t_actor
		A6 - t_actor
		A5 - t_actor
		A7 - t_actor
		A9 - t_actor
		foo - t_gtype
		G7 - t_goal
		G8 - t_goal
		G9 - t_goal
		G4 - t_goal
		G15 - t_goal
		G3 - t_goal
		G14 - t_goal
		G6 - t_goal
		G13 - t_goal
		G5 - t_goal
		G12 - t_goal
		G0 - t_goal
		G19 - t_goal
		G18 - t_goal
		G2 - t_goal
		G17 - t_goal
		G1 - t_goal
		G16 - t_goal
		G32 - t_goal
		G31 - t_goal
		G30 - t_goal
		G11 - t_goal
		G10 - t_goal
		G28 - t_goal
		G27 - t_goal
		G24 - t_goal
		G26 - t_goal
		G25 - t_goal
		G20 - t_goal
		G22 - t_goal
		G21 - t_goal
	)
	(:goal
		(and 
		(satisfied G0)
		(satisfied G9)
		(satisfied G15)
		)
	)
	(:init
		(= (max_job_at_hand A1) 5)
		(= (max_job_at_hand A2) 5)
		(= (max_job_at_hand A3) 5)
		(= (max_job_at_hand A4) 5)
		(= (max_job_at_hand A5) 7)
		(= (max_job_at_hand A6) 5)
		(= (max_job_at_hand A7) 5)
		(= (max_job_at_hand A9) 5)
		(= (max_satisfaction_degree G10) 200)
		(= (max_satisfaction_degree G14) 100)
		(= (max_satisfaction_degree G15) 100)
		(= (max_satisfaction_degree G16) 100)
		(= (max_satisfaction_degree G17) 100)
		(= (max_satisfaction_degree G19) 100)
		(= (max_satisfaction_degree G2) 200)
		(= (max_satisfaction_degree G20) 100)
		(= (max_satisfaction_degree G22) 100)
		(= (max_satisfaction_degree G24) 100)
		(= (max_satisfaction_degree G25) 100)
		(= (max_satisfaction_degree G26) 100)
		(= (max_satisfaction_degree G3) 200)
		(= (max_satisfaction_degree G30) 100)
		(= (max_satisfaction_degree G31) 100)
		(= (max_satisfaction_degree G32) 100)
		(= (max_satisfaction_degree G4) 200)
		(= (max_satisfaction_degree G5) 200)
		(= (max_satisfaction_degree G6) 200)
		(= (max_satisfaction_degree G7) 200)
		(= (max_work_load A1) 500)
		(= (max_work_load A2) 500)
		(= (max_work_load A3) 500)
		(= (max_work_load A4) 500)
		(= (max_work_load A5) 700)
		(= (max_work_load A6) 500)
		(= (max_work_load A7) 500)
		(= (max_work_load A9) 500)
		(= (satisfaction_ability A1 G25) 100)
		(= (satisfaction_ability A2 G10) 75)
		(= (satisfaction_ability A2 G2) 75)
		(= (satisfaction_ability A2 G4) 75)
		(= (satisfaction_ability A3 G2) 75)
		(= (satisfaction_ability A3 G4) 75)
		(= (satisfaction_ability A3 G5) 75)
		(= (satisfaction_ability A3 G6) 75)
		(= (satisfaction_ability A3 G7) 75)
		(= (satisfaction_ability A4 G15) 100)
		(= (satisfaction_ability A5 G10) 100)
		(= (satisfaction_ability A5 G14) 100)
		(= (satisfaction_ability A5 G16) 100)
		(= (satisfaction_ability A5 G17) 100)
		(= (satisfaction_ability A5 G2) 100)
		(= (satisfaction_ability A5 G24) 100)
		(= (satisfaction_ability A5 G32) 100)
		(= (satisfaction_ability A5 G4) 100)
		(= (satisfaction_ability A5 G5) 100)
		(= (satisfaction_ability A5 G6) 100)
		(= (satisfaction_ability A5 G7) 100)
		(= (satisfaction_ability A6 G10) 100)
		(= (satisfaction_ability A6 G19) 100)
		(= (satisfaction_ability A6 G20) 100)
		(= (satisfaction_ability A7 G26) 100)
		(= (satisfaction_ability A9 G22) 100)
		(= (satisfaction_ability A9 G30) 100)
		(= (satisfaction_ability A9 G31) 100)
		(= (satisfaction_coeff) 1)
		(= (time_effort A1 G25) 10)
		(= (time_effort A2 G10) 10)
		(= (time_effort A2 G2) 10)
		(= (time_effort A2 G4) 10)
		(= (time_effort A3 G2) 10)
		(= (time_effort A3 G4) 10)
		(= (time_effort A3 G5) 10)
		(= (time_effort A3 G6) 10)
		(= (time_effort A3 G7) 10)
		(= (time_effort A4 G15) 10)
		(= (time_effort A5 G10) 10)
		(= (time_effort A5 G14) 10)
		(= (time_effort A5 G16) 10)
		(= (time_effort A5 G17) 10)
		(= (time_effort A5 G2) 10)
		(= (time_effort A5 G24) 10)
		(= (time_effort A5 G32) 10)
		(= (time_effort A5 G4) 10)
		(= (time_effort A5 G5) 10)
		(= (time_effort A5 G6) 10)
		(= (time_effort A5 G7) 10)
		(= (time_effort A6 G10) 10)
		(= (time_effort A6 G19) 10)
		(= (time_effort A6 G20) 10)
		(= (time_effort A7 G26) 10)
		(= (time_effort A9 G22) 10)
		(= (time_effort A9 G30) 10)
		(= (time_effort A9 G31) 10)
		(= (work_effort A1 G25) 50)
		(= (work_effort A2 G10) 60)
		(= (work_effort A2 G2) 60)
		(= (work_effort A2 G4) 60)
		(= (work_effort A3 G2) 50)
		(= (work_effort A3 G4) 50)
		(= (work_effort A3 G5) 50)
		(= (work_effort A3 G6) 50)
		(= (work_effort A3 G7) 50)
		(= (work_effort A4 G15) 25)
		(= (work_effort A5 G10) 25)
		(= (work_effort A5 G14) 25)
		(= (work_effort A5 G16) 25)
		(= (work_effort A5 G17) 25)
		(= (work_effort A5 G2) 25)
		(= (work_effort A5 G24) 25)
		(= (work_effort A5 G32) 25)
		(= (work_effort A5 G4) 25)
		(= (work_effort A5 G5) 25)
		(= (work_effort A5 G6) 25)
		(= (work_effort A5 G7) 25)
		(= (work_effort A6 G10) 25)
		(= (work_effort A6 G19) 25)
		(= (work_effort A6 G20) 25)
		(= (work_effort A7 G26) 25)
		(= (work_effort A9 G22) 25)
		(= (work_effort A9 G30) 25)
		(= (work_effort A9 G31) 25)
		(= (work_load_coeff) 1)
		(and_subgoal2 G1 G2 G4)
		(and_subgoal2 G18 G24 G25)
		(and_subgoal2 G21 G19 G20)
		(and_subgoal2 G27 G30 G31)
		(and_subgoal3 G0 G1 G8 G26)
		(and_subgoal3 G11 G16 G17 G27)
		(and_subgoal3 G12 G13 G14 G18)
		(and_subgoal3 G13 G25 G30 G32)
		(and_subgoal3 G28 G10 G21 G22)
		(and_subgoal3 G8 G5 G6 G7)
		(and_subgoal3 G9 G28 G11 G12)
		(requests A1 G0)
		(requests A4 G32)
		(requests A4 G9)
		(requests A5 G15)
		(can_provide A1 G25)
		(can_provide A2 G2)
		(can_provide A2 G4)
		(can_provide A2 G10)
		(can_provide A3 G2)
		(can_provide A3 G4)
		(can_provide A3 G5)
		(can_provide A3 G6)
		(can_provide A3 G7)
		(can_provide A4 G15)
		(can_provide A5 G2)
		(can_provide A5 G4)
		(can_provide A5 G5)
		(can_provide A5 G6)
		(can_provide A5 G7)
		(can_provide A5 G10)
		(can_provide A5 G14)
		(can_provide A5 G16)
		(can_provide A5 G17)
		(can_provide A5 G24)
		(can_provide A5 G32)
		(can_provide A6 G10)
		(can_provide A6 G19)
		(can_provide A6 G20)
		(can_provide A7 G26)
		(can_provide A9 G22)
		(can_provide A9 G30)
		(can_provide A9 G31)
	)
	(:metric maximize (rt_plan_quality))
)
