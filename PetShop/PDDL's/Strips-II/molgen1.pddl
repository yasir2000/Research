;; Molgen's rat insulin problem.

(define (problem rat-insulin-strips)
  (:domain molgen-strips)
  (:objects insulin-gene e-coli-exosome junk-exosome
	    e-coli junk antibiotic-1)
  (:init (molecule insulin-gene)
	 (molecule e-coli-exosome)
	 (molecule junk-exosome)
	 (molecule linker)
	 (bacterium e-coli)
	 (bacterium junk)
	 (antibiotic antibiotic-1)
	 (mRNA insulin-gene)
	 (cleavable e-coli-exosome)
	 (cleavable junk-exosome)
	 (accepts junk-exosome junk)
	 (accepts e-coli-exosome e-coli)
	 (resists antibiotic-1 e-coli-exosome))

  ;; original goal (see explanation in domain file):
  ;; (exists (?x ?y)
  ;;  (and (bacterium ?y) (molecule ?x)
  ;;       (contains insulin-gene ?x)
  ;;       (contains ?x ?y)
  ;;       (pure ?y)))
  (:goal (construct-solved insulin-gene))
  )
